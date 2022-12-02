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
 * Class ViewportEntity. Contains two location objects, for the northeast and southwest views of the place
 * FOR DATABASE USE
 */
data class ViewportEntity(
    val northeast: LocationEntity,
    val southwest: LocationEntity
)
