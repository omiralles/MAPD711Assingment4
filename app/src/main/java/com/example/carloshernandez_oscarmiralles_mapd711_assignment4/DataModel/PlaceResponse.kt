package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Class PlaceResponse. Contains a list of place
 * The retrofit API class return this object
 */
data class PlaceResponse(

    @SerializedName("results")
    @Expose
    val places: List<Place>
)
