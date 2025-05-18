package com.mjapp.rush.core.common

import com.mjapp.rush.domain.model.category.CategoryItem
import com.mjapp.rush.domain.model.product.ProductItem
import com.mjapp.rush.data.source.local.entity.CategoryEntity
import com.mjapp.rush.data.source.local.entity.ProductEntity

fun CategoryItem.toDomain(): CategoryItem {
    return CategoryItem(
        uuid = uuid,
        name = name,
        view_all_enabled = view_all_enabled
    )
}

fun ProductEntity.toDomain(): ProductItem {
    return ProductItem(
        uuid = uuid,
        merchant_uuid = merchant_uuid,
        name = name,
        description = description,
        category_id = category_id,
        type = type,
        enable_special_discount = enable_special_discount,
        is_available_on_estore = is_available_on_estore,
        is_available_on_dine_in = is_available_on_dine_in,
        deleted_at = deleted_at,
        raw_price = raw_price,
        price = price,
        images = images,
        category = category
    )
}

fun CategoryItem.toEntity(): CategoryEntity {
    return CategoryEntity(
        uuid = uuid!!,
        name = name,
        view_all_enabled = view_all_enabled
    )
}

fun ProductItem.toEntity(): ProductEntity {
    return ProductEntity(
        uuid = uuid!!,
        merchant_uuid = merchant_uuid,
        name = name,
        description = description,
        category_id = category_id,
        type = type,
        enable_special_discount = enable_special_discount,
        is_available_on_estore = is_available_on_estore,
        is_available_on_dine_in = is_available_on_dine_in,
        deleted_at = deleted_at,
        raw_price = raw_price,
        price = price,
        images = images,
        category = category
    )
}