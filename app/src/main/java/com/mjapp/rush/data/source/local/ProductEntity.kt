package com.mjapp.rush.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjapp.rush.data.model.entities.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "merchant_uuid") val merchantUuid: String,
    val name: String,
    val description: String?,
    val price: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?
) {
    fun toDomain(): Product = Product(uuid, merchantUuid, name, description, price, imageUrl)
}