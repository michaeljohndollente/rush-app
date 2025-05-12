package com.mjapp.rush.core

import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.data.model.category.CategoryItem
import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.data.model.product.ProductItem
import com.mjapp.rush.data.source.local.CategoryEntity
import com.mjapp.rush.data.source.local.ProductEntity

fun CategoryItem.toDomain(): Category = Category(uuid = uuid.orEmpty(), name = name.orEmpty(), viewAllEnabled = viewAllEnabled == 1)
fun Category.toEntity(): CategoryEntity = CategoryEntity(uuid = uuid, name = name, viewAllEnabled = viewAllEnabled)
fun ProductItem.toDomain(): Product = Product(
    uuid = uuid.orEmpty(),
    merchantUuid = merchantUuid.orEmpty(),
    name = name.orEmpty(),
    description = description,
    price = price.orEmpty(),
    imageUrl = images?.firstOrNull()?.imageName?.let { "https://your-image-base-url/$it" } // Adjust URL as needed
)
fun Product.toEntity(): ProductEntity = ProductEntity(
    uuid = uuid,
    merchantUuid = merchantUuid,
    name = name,
    description = description,
    price = price,
    imageUrl = imageUrl
)
