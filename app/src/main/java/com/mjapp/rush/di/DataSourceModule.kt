package com.mjapp.rush.di

import com.mjapp.rush.core.network.ApiService
import com.mjapp.rush.data.source.local.LocalDataSource
import com.mjapp.rush.data.source.local.dao.CategoryDao
import com.mjapp.rush.data.source.local.dao.ProductDao
import com.mjapp.rush.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        categoryDao: CategoryDao,
        productDao: ProductDao
    ): LocalDataSource {
        return LocalDataSource(categoryDao, productDao)
    }
}