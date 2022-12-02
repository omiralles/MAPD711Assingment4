package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Util

import androidx.room.TypeConverter
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database.PlaceEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/

/**
 * Class ModelToEntityPlaceConverter.
 * Convert between List of PlaceEntity and String
 * to store or recover from the database
 * FOR DATABASE USE
 */
object EntityTypeConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<PlaceEntity> {
        val listType: Type = object : TypeToken<ArrayList<PlaceEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<PlaceEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}