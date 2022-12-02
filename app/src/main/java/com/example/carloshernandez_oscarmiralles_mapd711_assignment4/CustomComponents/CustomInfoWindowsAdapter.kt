package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.CustomComponents
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.PlaceDetails
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.R
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.SearchPlacesActivity
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.ViewModel.PlaceViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

/**
 * Class CustomInfoWindowsAdapter implements GoogleMap.InfoWindowAdapter
 * Create a custom Info Windows Adapter for the google map. This allow show customize info
 */
class CustomInfoWindowsAdapter(mContext: Context) : GoogleMap.InfoWindowAdapter {
    var mWindow: View = LayoutInflater.from(mContext).inflate(R.layout.googlemapsmarket_custom_window, null)

    /**
     * fun setInfoWindowText set the info for the google map market
     * Set the info in the marker (title and snippet)
     * @param market Market to fill out
     */
    private fun setInfoWindowText(marker: Marker) {
        val title = marker.title
        var snippet = marker.snippet

        val view_title = mWindow.findViewById<TextView>(R.id.mark_name)
        val view_raiting = mWindow.findViewById<TextView>(R.id.mark_rating)
        if (!TextUtils.isEmpty(title)) {
            view_title.text = title
        }
        if (!TextUtils.isEmpty(snippet)) {
            view_raiting.text = "Rating: "+snippet
        }
    }

    /**
     * Override fun getInfoWindow
     * @param p0 as GoogleMaps.Marker
     * @return View created with all the info
     */
    override fun getInfoWindow(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }

    /**
     * Override getInfoContents
     * @param p0 as GoogleMaps.Marker
     * @return View created with all the info
     */
    override fun getInfoContents(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }
}