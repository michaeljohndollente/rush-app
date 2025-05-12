package com.mjapp.rush.core.common

import com.mjapp.rush.data.model.category.CategoryItem
import com.mjapp.rush.data.model.product.ProductItem
import com.mjapp.rush.data.source.local.entity.CategoryEntity
import com.mjapp.rush.data.source.local.entity.ProductEntity
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.domain.model.Product

fun CategoryItem.toDomain(): Category = Category(uuid = uuid!!, name = name, view_all_enabled = view_all_enabled)
fun Category.toEntity(): CategoryEntity =
    CategoryEntity(uuid = uuid, name = name, view_all_enabled = view_all_enabled)

fun ProductItem.toEntity(branchUuid: String, brandUuid: String): ProductEntity =
   ProductEntity(
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
       image_uuids = images?.joinToString(",") { it.uuid ?: "" },
       category_uuid = category?.uuid
    )

fun CategoryEntity.toDomain(): Category = Category(uuid, name, view_all_enabled)

fun ProductEntity.toDomain(): Product = Product(
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
    image_urls = image_uuids?.split(",")?.filterNot { it.isEmpty() } ?: emptyList(),
    category = category_uuid
)

fun ProductItem.toDomain(): Product {
    return Product(
        uuid = uuid ?: "",
        merchant_uuid = merchant_uuid,
        name = name,
        description = description ?: "",
        category_id = category_id,
        type = type,
        enable_special_discount = enable_special_discount,
        is_available_on_estore = is_available_on_estore,
        is_available_on_dine_in = is_available_on_dine_in,
        deleted_at = deleted_at,
        raw_price = raw_price,
        price = price,
        image_urls = images?.mapNotNull { it.uuid } ?: emptyList(),
        category = category?.uuid
    )
}