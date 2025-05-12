package com.mjapp.rush.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mjapp.rush.data.source.local.CategoryDao
import com.mjapp.rush.data.source.local.CategoryEntity
import com.mjapp.rush.data.source.local.ProductDao
import com.mjapp.rush.data.source.local.ProductEntity

@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "estore_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}