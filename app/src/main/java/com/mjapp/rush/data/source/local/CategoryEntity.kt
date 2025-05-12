package com.mjapp.rush.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjapp.rush.data.model.entities.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val uuid: String,
    val name: String,
    @ColumnInfo(name = "view_all_enabled") val viewAllEnabled: Boolean
) {
    fun toDomain(): Category = Category(uuid, name, viewAllEnabled)
}