package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Repo.network
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class PlaceService. Service class for retrofit API
 * Create a retrofit object to get data from the Places API
 */
object PlaceService {

    var baseURL = "https://maps.googleapis.com/maps/api/place/"

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //API instance
    val retrofit = retrofitBuilder.create(PlacesRetrofitAPI::class.java)

}