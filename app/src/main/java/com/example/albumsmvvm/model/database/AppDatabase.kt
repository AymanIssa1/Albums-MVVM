package com.example.albumsmvvm.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.albumsmvvm.model.Album
import com.example.albumsmvvm.model.AlbumDao

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}