package com.mjapp.rush.di

import com.mjapp.rush.data.repository.StoreRepositoryImpl
import com.mjapp.rush.data.source.local.LocalDataSource
import com.mjapp.rush.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideStoreRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): StoreRepositoryImpl {
        return StoreRepositoryImpl(remoteDataSource, localDataSource)
    }
}