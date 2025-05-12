package com.mjapp.rush.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mjapp.rush.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM products WHERE merchant_uuid = :branchUuid AND merchant_uuid = :brandUuid LIMIT :limit OFFSET :offset")
    fun getProducts(branchUuid: String, brandUuid: String, limit: Int, offset: Int): Flow<List<ProductEntity>>

    @Query("DELETE FROM products WHERE merchant_uuid = :branchUuid AND merchant_uuid = :brandUuid")
    suspend fun clearProducts(branchUuid: String, brandUuid: String)
}