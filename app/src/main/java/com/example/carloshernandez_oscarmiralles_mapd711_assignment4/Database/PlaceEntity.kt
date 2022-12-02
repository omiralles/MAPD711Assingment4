package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/

/**
 * Class PlaceEntity. Contains a place, place_id, name, rating and place geometry
 * FOR DATABASE USE
 */
data class PlaceEntity(
    val place_id: String,
    val name: String,
    val rating: Float,
    val placeGeometry: PlaceGeometryEntity
)
