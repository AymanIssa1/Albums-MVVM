package com.example.albumsmvvm.network

import com.example.albumsmvvm.model.Album
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumApi {
    /**
     * Get the list of the albums from the API
     */
    @GET("/albums")
    fun getAlbums(): Observable<List<Album>>
}