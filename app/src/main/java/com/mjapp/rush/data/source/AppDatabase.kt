package com.mjapp.rush.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mjapp.rush.data.source.local.dao.CategoryDao
import com.mjapp.rush.data.source.local.dao.ProductDao
import com.mjapp.rush.data.source.local.entity.CategoryEntity
import com.mjapp.rush.data.source.local.entity.ProductEntity
import com.mjapp.rush.data.source.local.entity.converters.CategoryInfoConverter
import com.mjapp.rush.data.source.local.entity.converters.ImagesListConverter

@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    ImagesListConverter::class,
    CategoryInfoConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}