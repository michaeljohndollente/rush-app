package com.mjapp.rush.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mjapp.rush.data.source.local.entity.converters.CategoryInfoConverter
import com.mjapp.rush.data.source.local.entity.converters.ImagesListConverter
import com.mjapp.rush.domain.model.product.CategoryInfo
import com.mjapp.rush.domain.model.product.Images

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val uuid: String,
    val merchant_uuid: String?,
    val name: String?,
    val description: String?,
    val category_id: Int?,
    val type: String?,
    val enable_special_discount: Int?,
    val is_available_on_estore: Int?,
    val is_available_on_dine_in: Int?,
    val deleted_at: String?,
    val raw_price: String?,
    val price: String?,
    @TypeConverters(ImagesListConverter::class)
    val images: List<Images>?,
    @TypeConverters(CategoryInfoConverter::class)
    val category: CategoryInfo?
)
