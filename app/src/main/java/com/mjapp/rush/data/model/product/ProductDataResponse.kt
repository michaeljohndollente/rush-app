package com.mjapp.rush.data.model.product

data class ProductDataResponse(
    val listData: List<ProductItem>?,
    val maxPage: Int?,
    val prevPage: Int?,
    val nextPage: Int?,
    val message: String?
)