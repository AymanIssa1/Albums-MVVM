package com.example.albumsmvvm.di.component

import com.example.albumsmvvm.di.module.NetworkModule
import com.example.albumsmvvm.ui.AlbumListViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified AlbumListViewModel.
     * @param albumListViewModel AlbumListViewModel in which to inject the dependencies
     */
    fun inject(albumListViewModel: AlbumListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }

}