package com.example.carloshernandez_oscarmiralles_mapd711_assignment4.Database
/**
 * MAPD711 Assignment 3
 * @Authors
 * Student Name: Oscar Miralles Fernandez
 * Student ID: 301250756
 * Student Name: Carlos Hernandez Galvan
 * Student ID: 301290263
 **/
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Class PlaceResponseEntity. Contains a list of place
 * FOR DATABASE USE
 */
@Entity(tableName = "places")
data class PlaceResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @ColumnInfo(name = "places") val places: List<PlaceEntity>,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "city") val city: String
)
