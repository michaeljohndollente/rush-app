package com.mjapp.rush.di

import android.content.Context
import androidx.room.Room
import com.mjapp.rush.core.network.ApiService
import com.mjapp.rush.core.network.RetrofitInstance
import com.mjapp.rush.data.source.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "estore_database"
        ).build()
    }

    @Provides
    fun provideCategoryDao(database: AppDatabase) = database.categoryDao()

    @Provides
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        // Provide a function to get the token when needed
        val tokenProvider: suspend () -> String? = { null } // Replace with actual token fetching logic if needed here
        return RetrofitInstance.getApiService(tokenProvider)
    }
}