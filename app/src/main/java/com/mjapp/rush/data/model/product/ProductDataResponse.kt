package com.mjapp.rush.data.model.product

data class ProductDataResponse(
    val list: List<ProductItem>?,
    val max_page: Int?,
    val prev_page: Int?,
    val next_page: Int?,
    val message: String?
)