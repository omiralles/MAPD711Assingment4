package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvez
 * Student ID: 301290263
 **/

/**
 * Class Place Geometry Entity. Contains details about location, a location object and viewport object
 * FOR DATABASE USE
 */
data class PlaceGeometryEntity(
    val location: LocationEntity,
    val viewport: ViewportEntity
)
