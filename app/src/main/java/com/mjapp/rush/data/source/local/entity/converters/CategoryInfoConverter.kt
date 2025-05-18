package com.mjapp.rush.data.source.local.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mjapp.rush.domain.model.product.CategoryInfo

class CategoryInfoConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCategoryInfo(categoryInfo: CategoryInfo?): String? {
        return gson.toJson(categoryInfo)
    }

    @TypeConverter
    fun toCategoryInfo(categoryInfoString: String?): CategoryInfo? {
        return gson.fromJson(categoryInfoString, CategoryInfo::class.java)
    }
}