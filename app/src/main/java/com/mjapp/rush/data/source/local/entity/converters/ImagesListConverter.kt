package com.mjapp.rush.data.source.local.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mjapp.rush.domain.model.product.Images
import java.lang.reflect.Type

class ImagesListConverter {

    private val gson = Gson()
    private val type: Type = object : TypeToken<List<Images>>() {}.type

    @TypeConverter
    fun fromImageList(imageList: List<Images>?): String? {
        return gson.toJson(imageList)
    }

    @TypeConverter
    fun toImageList(imageListString: String?): List<Images>? {
        return gson.fromJson(imageListString, type)
    }
}