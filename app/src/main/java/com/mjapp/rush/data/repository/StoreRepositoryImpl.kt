package com.mjapp.rush.data.repository

import android.util.Log
import kotlinx.coroutines.*

import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.core.common.toDomain
import com.mjapp.rush.core.common.toEntity
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.source.local.LocalDataSource
import com.mjapp.rush.data.source.remote.RemoteDataSource
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.domain.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.map

@Singleton
class StoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): StoreRepository {
    private var cachedToken: String? = null

    override suspend fun getAuthToken(): String? {
        return cachedToken ?: runCatching {
            val tokenResponse = remoteDataSource.getMerchantToken()
            tokenResponse.token.also { cachedToken = it }
        }.getOrNull()
    }

    override fun getCategories(branchUuid: String): Flow<DataState<List<Category>>> = flow {
        emit(DataState.Loading)
        val authToken = getAuthToken()
        if (authToken == null) {
            return@flow
        }
        val response = remoteDataSource.getCategories(authToken, branchUuid)
        if (response.status == 200 && response.data?.categories != null) {
            Log.d("StoreRepo", "CategoryItem from API: ${response.data.categories}")
            withContext(Dispatchers.IO) {
                val categoryDomain = response.data.categories.map { it!!.toDomain() }
                val categoryEntities = categoryDomain.map { it.toEntity() }

                localDataSource.clearCategories()
                localDataSource.insertCategories(categoryEntities)
                localDataSource.getAllCategories().collect { entities ->
                    Log.d("StoreRepo", "Local categories after insert: $entities")
                }
            }
            emit(DataState.Success(response.data.categories.map { it!!.toDomain() }))
        } else {
            emit(DataState.Error(response.message ?: "Failed to fetch categories."))
        }
    }.catch { e ->
        emit(DataState.Error("Network error: ${e.message}"))
        localDataSource.getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }.catch { emit(emptyList()) }.collect { localCategories ->
            emit(DataState.Success(localCategories))
            Log.d("StoreRepo", "Loaded local categories on error: $localCategories")
        }
    }

    override fun getProductList(page: Int, branchUuid: String, brandUuid: String): Flow<DataState<ProductDataResponse>> = flow {
        emit(DataState.Loading)
        val authToken = getAuthToken()
        if (authToken == null) {
            return@flow
        }
        val response = remoteDataSource.getProductList(authToken, page, branchUuid, brandUuid)
        if (response.list != null) {
            withContext(Dispatchers.IO) {
                val productEntities = response.list.map { it.toEntity(branchUuid, brandUuid) }
                localDataSource.insertProducts(productEntities)
            }
            emit(DataState.Success(response))
        } else {
            emit(DataState.Error(response.message ?: "Failed to fetch product list."))
        }
    }.catch { e ->
        emit(DataState.Error("Network error: ${e.message}"))
    }

}