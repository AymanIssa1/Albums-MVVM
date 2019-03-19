package com.example.albumsmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class which provides a model for album
 * @constructor Sets all properties of the album
 * @property userId the unique identifier of the author of the album
 * @property id the unique identifier of the album
 * @property title the title of the album
 */
@Entity
data class Album(
    val userId: Int,
    @field:PrimaryKey val id: Int,
    val title: String
)