package com.example.albumsmvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {

    @get:Query("SELECT * FROM album ORDER BY title")
    val getAllAlbums: List<Album>


    @Insert
    fun insertAll(vararg albums: Album)

}