package com.example.albumsmvvm.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.albumsmvvm.model.database.AppDatabase
import com.example.albumsmvvm.ui.AlbumListViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "albums").build()
            return AlbumListViewModel(db.albumDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}