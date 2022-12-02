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
 * Class PlaceDetails. Contains a place details like address, name, international phone
 * number, rating and website
 */
data class PlaceDetails(
    @SerializedName("formatted_address")
    val address: String,
    val name: String,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("international_phone_number")
    val internationalPhoneNumber: String,
    val rating: Float,
    val website: String
){

    fun convertIntoSnippet(): String {
        return "$name;$address;$internationalPhoneNumber;$rating;$website"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode(): Int {
        return placeId.hashCode()
    }
}
