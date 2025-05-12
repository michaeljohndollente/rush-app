package com.mjapp.rush.data.model.entities

data class Product(
    val uuid: String,
    val merchantUuid: String,
    val name: String,
    val description: String?,
    val price: String,
    val imageUrl: String?
)