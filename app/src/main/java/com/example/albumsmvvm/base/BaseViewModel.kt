package com.example.albumsmvvm.base

import androidx.lifecycle.ViewModel
import com.example.albumsmvvm.di.component.DaggerViewModelInjector
import com.example.albumsmvvm.di.component.ViewModelInjector
import com.example.albumsmvvm.di.module.NetworkModule
import com.example.albumsmvvm.ui.AlbumListViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is AlbumListViewModel -> injector.inject(this)
        }
    }

}