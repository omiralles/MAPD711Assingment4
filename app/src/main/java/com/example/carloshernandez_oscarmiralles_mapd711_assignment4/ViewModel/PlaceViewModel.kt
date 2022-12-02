package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.ViewModel
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.PlaceDetails
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.PlaceResponse
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Repo.network.PlaceService
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.SearchPlacesActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Class PlaceViewModel. Create a ViewModel object
 * Contains places and details about places ViewModel
 * @see PlaceService
 * @see placesRepo
 */
class PlaceViewModel: ViewModel() {

    private val _placesLiveData = MutableLiveData<PlaceResponse>()
    val placesLiveData: LiveData<PlaceResponse> = _placesLiveData
    //private val _placeDetails = MutableLiveData<PlaceDetails>()
    //val placeDetails = LiveData<PlaceDetails> = _placeDetails

    /*
    init {
        if (placesLiveData == null){
            placesLiveData = MutableLiveData()
        }
        if (placeDetails == null){
            placeDetails = MutableLiveData()
        }
    }*/


    /**
     * fun getNearPlacesTesterWithParameters
     * @param category String Category what is looking
     * @param location String Location (latitude, longitude) for the city is looking for
     */
    fun getNearPlacesTesterWithParameters(forceFetch:Boolean, category: String, location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = SearchPlacesActivity.placesRepo?.getPlaces(forceFetch,location,category)
            //val data = PlaceService.retrofit.getPlacesNear(category = category, location = location)
            _placesLiveData.postValue(data!!)
        }
    }

    /*

    fun getPlaceDetails(placeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = PlaceService.retrofit.getPlaceDetails(place_id = placeId)
            placeDetails?.postValue(data)
        }
    }*/
}