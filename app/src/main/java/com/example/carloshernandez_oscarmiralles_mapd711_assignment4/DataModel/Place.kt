package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/
import com.google.gson.annotations.SerializedName

/**
 * Class Place. Contains a place, place_id, name, rating and place geometry
 */
data class Place(
    @SerializedName("place_id")
    val place_id: String,
    val name: String,
    val rating: Float,
    @SerializedName("geometry")
    val placeGeometry: PlaceGeometry
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode(): Int {
        return place_id.hashCode()
    }
}


