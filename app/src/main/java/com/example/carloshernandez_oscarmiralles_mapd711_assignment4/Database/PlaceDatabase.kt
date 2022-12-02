package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Util.EntityTypeConverter
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/

/**
 * Abstract class PlaceDatabase
 * FOR DATABASE USE
 */
@Database(entities = [PlaceResponseEntity::class], version = 2)
@TypeConverters(EntityTypeConverter::class)
abstract class PlaceDatabase: RoomDatabase() {
    abstract fun placesDao(): PlacesDao
}