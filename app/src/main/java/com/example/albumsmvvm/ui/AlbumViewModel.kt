package com.example.albumsmvvm.ui

import androidx.lifecycle.MutableLiveData
import com.example.albumsmvvm.base.BaseViewModel
import com.example.albumsmvvm.model.Album


class AlbumViewModel : BaseViewModel() {

    private val albumTitle = MutableLiveData<String>()


    fun bind(album: Album) {
        albumTitle.value = album.title
    }

    fun getAlbumTitle(): MutableLiveData<String> = albumTitle

}