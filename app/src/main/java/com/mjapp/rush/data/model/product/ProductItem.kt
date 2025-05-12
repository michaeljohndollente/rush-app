package com.mjapp.rush.data.model.product

data class ProductItem(
    val uuid: String?,
    val merchantUuid: String?,
    val name: String?,
    val description: String??,
    val categoryId: Int?,
    val type: String?,
    val enableSpecialDiscount: Int?,
    val isAvailableOnEstore: Int?,
    val isAvailableOnDineIn: Int?,
    val deletedAt: String?,
    val rawPrice: String?,
    val price: String?,
    val images: List<ProductImage>?,
    val category: ProductCategory?
)