package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database

import androidx.room.*
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/

/**
 * Interface PlaceDao. Queries to the database
 * FOR DATABASE USE
 */

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(placeResponseEntity: PlaceResponseEntity)

    @Query("SELECT * FROM places WHERE category = :category AND city = :city")
    suspend fun getData(category: String, city: String): PlaceResponseEntity?

    @Query("DELETE FROM places")
    suspend fun deleteData()

    @Delete
    suspend fun deleteFromDatabase(placesEntity: PlaceResponseEntity)
}