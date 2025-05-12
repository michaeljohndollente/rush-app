package com.mjapp.rush.di

import com.mjapp.rush.data.repository.StoreRepositoryImpl
import com.mjapp.rush.domain.usecase.GetCategoriesUseCase
import com.mjapp.rush.domain.usecase.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetCategoriesUseCase(repository: StoreRepositoryImpl): GetCategoriesUseCase {
        return GetCategoriesUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetProductListUseCase(repository: StoreRepositoryImpl): GetProductListUseCase {
        return GetProductListUseCase(repository)
    }
}