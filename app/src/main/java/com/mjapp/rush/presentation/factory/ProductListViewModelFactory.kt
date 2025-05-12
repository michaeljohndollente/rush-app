package com.mjapp.rush.presentation.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mjapp.rush.data.remote.RetrofitClient
import com.mjapp.rush.data.repository.CategoryRepositoryImpl
import com.mjapp.rush.data.repository.GetCachedCategoriesUseCaseImpl
import com.mjapp.rush.data.repository.GetCachedProductListUseCaseImpl
import com.mjapp.rush.data.repository.GetCategoriesUseCaseImpl
import com.mjapp.rush.data.repository.GetProductListUseCaseImpl
import com.mjapp.rush.data.repository.ProductRepositoryImpl
import com.mjapp.rush.data.source.AppDatabase
import com.mjapp.rush.data.source.repository.CategoryLocalDataSource
import com.mjapp.rush.data.source.repository.CategoryRemoteDataSource
import com.mjapp.rush.data.source.repository.ProductLocalDataSource
import com.mjapp.rush.data.source.repository.ProductRemoteDataSource
import com.mjapp.rush.presentation.viewmodel.ProductListViewModel

class ProductListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    private val database by lazy { AppDatabase.getDatabase(application) }
    private val categoryRemoteDataSource by lazy { CategoryRemoteDataSource(RetrofitClient.apiService) }
    private val productRemoteDataSource by lazy { ProductRemoteDataSource(RetrofitClient.apiService) }
    private val categoryLocalDataSource by lazy { CategoryLocalDataSource(database.categoryDao()) }
    private val productLocalDataSource by lazy { ProductLocalDataSource(database.productDao()) }
    private val categoryRepository by lazy { CategoryRepositoryImpl(categoryRemoteDataSource, categoryLocalDataSource) }
    private val productRepository by lazy { ProductRepositoryImpl(productRemoteDataSource, productLocalDataSource) }
    private val getCategoriesUseCase by lazy { GetCategoriesUseCaseImpl(categoryRepository) }
    private val getProductListUseCase by lazy { GetProductListUseCaseImpl(productRepository) }
    private val getCachedCategoriesUseCase by lazy { GetCachedCategoriesUseCaseImpl(categoryRepository) }
    private val getCachedProductListUseCase by lazy { GetCachedProductListUseCaseImpl(productRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductListViewModel(
                getCategoriesUseCase,
                getProductListUseCase,
                getCachedCategoriesUseCase,
                getCachedProductListUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}