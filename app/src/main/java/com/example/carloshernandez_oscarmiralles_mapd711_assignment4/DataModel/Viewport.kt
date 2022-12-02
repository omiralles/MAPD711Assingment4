package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.DataModel
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/

/**
 * Class Viewport. Contains two location objects, for the northeast and southwest views of the place
 */
data class Viewport(
    val northeast: Location,
    val southwest: Location
)
