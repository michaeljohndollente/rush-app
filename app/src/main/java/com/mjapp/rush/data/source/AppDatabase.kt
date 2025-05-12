package com.mjapp.rush.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mjapp.rush.data.source.local.dao.CategoryDao
import com.mjapp.rush.data.source.local.dao.ProductDao
import com.mjapp.rush.data.source.local.entity.CategoryEntity
import com.mjapp.rush.data.source.local.entity.ProductEntity

@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
}