package com.example.carloshernandez_oscarmiralles_mapd711_assignment4
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.CustomComponents.CustomInfoWindowsAdapter
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.Place
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Repo.placesRepo
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.ViewModel.PlaceViewModel
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.databinding.ActivitySearchPlacesBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Class SearchPlacesActivity (Activity class)
 * Implements AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener
 * Set all the view configuration, create view model and retrofit object to get and store the data
 */
class SearchPlacesActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener {

    companion object {
        var placesRepo: placesRepo? = null
        private const val LOG_TAG = "Assignment4_SearchPlacesActivity"
    }

    // Variables
    lateinit var binding: ActivitySearchPlacesBinding
    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: PlaceViewModel

    // Views variables
    lateinit var spinnerCities: Spinner
    lateinit var spinnerCategories: Spinner

    // Data variables (used for default options)
    var categorySelected: String = ""
    var citySelected: String = ""
    var latlngSelected: LatLng = LatLng(0.0,0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_search_places)
        binding = ActivitySearchPlacesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Create ViewModel and Repo
        viewModel  = ViewModelProvider(this)[PlaceViewModel::class.java]
        placesRepo = placesRepo(applicationContext)

        //Observe creation to get the product data and put it on components
        viewModel.placesLiveData.observe(this){
            Log.e(LOG_TAG,"=====================================")
            Log.e(LOG_TAG,"Place count: "+it.places.count())

            mMap.clear()

            for (place in it.places){
                Log.e(LOG_TAG,"Place name: "+place.name+ " - LatLng: "+ place.placeGeometry.location.toString())
                addMarkOnMap(mMap, place)
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(place.placeGeometry.location.lat,place.placeGeometry.location.lng), 12f))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(place.placeGeometry.location.lat,place.placeGeometry.location.lng), 12f))
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlngSelected, 12f))
        }

        // Load city spinner values
        spinnerCities = findViewById(R.id.search_places_city_spinner)
        spinnerCities.adapter = ArrayAdapter(
            this,
            R.layout.spinner_custom_selected,
            Cities.values())
        spinnerCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(parent?.context, "" + Cities.values()[position] + " Selected..", Toast.LENGTH_SHORT)
                    .show()
                citySelected = Cities.values()[position].locationString
                latlngSelected = Cities.values()[position].location
            }
        }

        // Load location categories spinner values
        spinnerCategories = findViewById(R.id.search_places_category_spinner)
        val adapter = ArrayAdapter(this,R.layout.spinner_custom_selected,Categories.values())
        adapter.setDropDownViewResource(R.layout.spinner_custom_dropdown)
        spinnerCategories.adapter = adapter
        spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(parent?.context, "" + Categories.values()[position] + " Selected..", Toast.LENGTH_SHORT)
                    .show()
                categorySelected = Categories.values()[position].c
            }
        }

        // Set Search button onclick
        val searchButton: Button = findViewById(R.id.search_places_search_button)
        searchButton.setOnClickListener {
            Toast.makeText(this, "Looking for $categorySelected", Toast.LENGTH_SHORT).show()
            viewModel.getNearPlacesTesterWithParameters(forceFetch = false, category = categorySelected, location = citySelected)
        }

        // Set search force fect onclick
        val forceFetchButton: Button = findViewById(R.id.search_places_refresh_button)
        forceFetchButton.setOnClickListener {
            Toast.makeText(this, "Download for $categorySelected", Toast.LENGTH_SHORT).show()
            viewModel.getNearPlacesTesterWithParameters(forceFetch = true, category = categorySelected, location = citySelected)
        }

        // Set change map type button
        val mapTypeButton: Button = findViewById(R.id.search_places_map_type_button)
        mapTypeButton.setOnClickListener {
            Toast.makeText(this, "Changing map type", Toast.LENGTH_SHORT).show()
            if (mMap.mapType ==  GoogleMap.MAP_TYPE_HYBRID){
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                mapTypeButton.text = getString(R.string.map_type_satellite)
            }else if (mMap.mapType ==  GoogleMap.MAP_TYPE_SATELLITE){
                mapTypeButton.text = getString(R.string.map_type_terrain)
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }else if (mMap.mapType ==  GoogleMap.MAP_TYPE_TERRAIN){
                mapTypeButton.text = getString(R.string.map_type_normal)
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }else if (mMap.mapType ==  GoogleMap.MAP_TYPE_NORMAL){
                mapTypeButton.text = getString(R.string.map_type_hybrid)
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
           .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * override fun onMapReady
     * @param googleMap GoogleMap object used in the view
     * Set the google map object when is loaded
     */
    override fun onMapReady(googleMap: GoogleMap) {
        //Init google map
        mMap = googleMap
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.setOnInfoWindowClickListener(this)
        mMap.setOnInfoWindowClickListener(this)
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap.setInfoWindowAdapter(CustomInfoWindowsAdapter(this))
        val startPoint = LatLng(19.42, -99.13)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPoint))
    }

    /**
     * private fun addMarkOnMap
     * @param googleMap GoogleMap map created in this activity
     * @param place Place object with the information to add a marker
     * Add a GoogleMap Marker in the map, according to a place info
     */
    private fun addMarkOnMap(googleMap: GoogleMap, place: Place){
        mMap = googleMap
        val location = LatLng(place.placeGeometry.location.lat, place.placeGeometry.location.lng)
        val mySnippet = place.rating.toString()

        mMap.addMarker(MarkerOptions()
            .position(location)
            .title(place.name)
            .snippet(mySnippet)
        )
    }

    override fun onInfoWindowClick(p0: Marker) {
        Toast.makeText(this, "Info window clicked",
            Toast.LENGTH_SHORT).show()
        /*viewModel.placeDetails?.observe(this){
            Log.e(LOG_TAG,"=====================================")
            Log.e(LOG_TAG,"Place id: "+it.name+" "+it.placeId)
        }*/
    }
}

/**
 * ENUM class Categories
 * @param c String category label used in Google Places API
 */
enum class Categories(val c: String){
    AIRPORTS("airport"),
    RESTAURANTS("restaurant"),
    MUSEUMS("museum"),
    BARS("bar"),
    CAFES("cafe"),
    PARKS("park"),
    STADIUMS("stadium"),
    HOSPITALS("hospital"),
    ATM("atm"),
}

/**
 * ENUM class Categories
 * @param location LatLng object with latitude and longitude for the city
 * @param locationString String String woth latitude and longitude, used to search places in
 *  Google Places API
 */
enum class Cities(val location: LatLng, val locationString: String) {
    MEXICO(LatLng(19.424356733700325, -99.13881714607176),"19.424356733700325,-99.13881714607176"),
    SIDNEY(LatLng(-33.8670522, 151.1957362),"-33.8670522,151.1957362"),
    VANCOUVER(LatLng(49.27847740889511, -123.12252083206138),"49.27847740889511,-123.12252083206138"),
    TORONTO(LatLng(43.64714678649412, -79.39042634283612),"43.64714678649412,-79.39042634283612"),
    BARCELONA(LatLng(41.39161647335966, 2.1580396343181256),"41.39161647335966,2.1580396343181256"),
    PARIS(LatLng(48.85363818564254, 2.3315834749498285),"48.85363818564254,2.3315834749498285"),
    LONDON(LatLng(51.510720036266825, -0.1314752882626475),"51.510720036266825,-0.1314752882626475"),
    ROME(LatLng(41.90551459763478, 12.481057349596519),"41.90551459763478,12.481057349596519"),
    TOKYO(LatLng(35.6821711350488, 139.75683370561998),"35.6821711350488,139.75683370561998"),
}