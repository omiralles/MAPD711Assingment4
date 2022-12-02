package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Util

import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel.*
import com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database.*
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/

/**
 * Class ModelToEntityPlaceConverter.
 * Convert between model(retrofit) and entity (Database) place objects
 */
object ModelToEntityPlaceConverter {

    fun fromPlaceResponseModelToPlaceResponseEntity(placeResponse: PlaceResponse, category: String, city: String): PlaceResponseEntity {
        return PlaceResponseEntity(
            places = fromPlaceModelToPlaceEntity(placeResponse.places),
            category = category,
            city = city
        )
    }

    fun fromPlaceResponseEntityToPlaceResponseModel(placeResponseEntity: PlaceResponseEntity): PlaceResponse {
        return PlaceResponse(
            places = fromPlaceEntityToPlaceModel(placeResponseEntity.places)
        )
    }

    private fun fromPlaceModelToPlaceEntity(places: List<Place>): List<PlaceEntity> {
        val list: ArrayList<PlaceEntity> = arrayListOf()
        places.forEach {
            list.add(
                PlaceEntity(
                    place_id = it.place_id,
                    name = it.name,
                    rating = it.rating,
                    placeGeometry = fromPlaceGeometryModelToPlaceGeometryEntity(it.placeGeometry)
                )
            )
        }
        return list
    }

    private fun fromPlaceEntityToPlaceModel(places: List<PlaceEntity>): List<Place> {
        val list: ArrayList<Place> = arrayListOf()
        places.forEach {
            list.add(
                Place(
                    place_id = it.place_id,
                    name = it.name,
                    rating = it.rating,
                    placeGeometry = fromPlaceGeometryEntityToPlaceGeometryModel(it.placeGeometry)
                )
            )
        }
        return list
    }

    private fun fromPlaceGeometryModelToPlaceGeometryEntity(placeGeometry: PlaceGeometry): PlaceGeometryEntity {
        return PlaceGeometryEntity(
            location = fromLocationModelToLocationEntity(placeGeometry.location),
            viewport = fromViewportModelToViewportEntity(placeGeometry.viewport)
        )
    }

    private fun fromPlaceGeometryEntityToPlaceGeometryModel(placeGeometryEntity: PlaceGeometryEntity): PlaceGeometry {
        return PlaceGeometry(
            location = fromLocationEntityToLocationModel(placeGeometryEntity.location),
            viewport = fromViewportEntityToViewportModel(placeGeometryEntity.viewport)
        )
    }

    private fun fromViewportModelToViewportEntity(viewport: Viewport): ViewportEntity{
        return ViewportEntity(
            northeast = fromLocationModelToLocationEntity(viewport.northeast),
            southwest = fromLocationModelToLocationEntity(viewport.southwest)
        )
    }

    private fun fromViewportEntityToViewportModel(viewportEntity: ViewportEntity): Viewport{
        return Viewport(
            northeast = fromLocationEntityToLocationModel(viewportEntity.northeast),
            southwest = fromLocationEntityToLocationModel(viewportEntity.southwest)
        )
    }

    private fun fromLocationModelToLocationEntity(location: Location): LocationEntity{
        return LocationEntity(
            lat = location.lat,
            lng = location.lng
        )
    }

    private fun fromLocationEntityToLocationModel(locationEntity: LocationEntity): Location{
        return Location(
            lat = locationEntity.lat,
            lng = locationEntity.lng
        )
    }

}