package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Repo

import android.content.Context
import androidx.room.Room
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.PlaceResponse
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database.PlaceDatabase
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Repo.network.PlaceService
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Util.ModelToEntityPlaceConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class placesRepo(appContext: Context) {

    private val database = Room.databaseBuilder(appContext, PlaceDatabase::class.java, "places").build()

    suspend fun getPlaces(forceFetch: Boolean, location: String, category: String): PlaceResponse {
        if (!forceFetch) {
            val data = withContext(Dispatchers.IO) {
                database.placesDao().getData(category, location)
            }

            if (data != null && data.places.isNotEmpty()) {
                return ModelToEntityPlaceConverter.fromPlaceResponseEntityToPlaceResponseModel(data)
            }
        }

        return withContext(Dispatchers.IO) {
            val value = PlaceService.retrofit.getPlacesNear(location = location, category = category)
            deleteDataFromDatabase()
            saveDataToDatabase(value, category, location)
            value
        }
    }

    private suspend fun saveDataToDatabase(placeResponse: PlaceResponse, category: String, city: String) {
        withContext(Dispatchers.IO) {
            database.placesDao().upsertAll(ModelToEntityPlaceConverter.fromPlaceResponseModelToPlaceResponseEntity(placeResponse, category, city))
        }
    }

    private suspend fun deleteDataFromDatabase() {
        withContext(Dispatchers.IO) {
            database.placesDao().deleteData()
        }
    }
}