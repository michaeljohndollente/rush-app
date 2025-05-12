package com.mjapp.rush.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val uuid: String,
    val name: String?,
    val view_all_enabled: Int?
)