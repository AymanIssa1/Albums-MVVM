package com.example.albumsmvvm.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.albumsmvvm.R
import com.example.albumsmvvm.base.BaseViewModel
import com.example.albumsmvvm.model.Album
import com.example.albumsmvvm.model.AlbumDao
import com.example.albumsmvvm.network.AlbumApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumListViewModel(private val albumDao: AlbumDao) : BaseViewModel() {

    @Inject
    lateinit var albumApi: AlbumApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadAlbums() }

    val albumListAdapter: AlbumListAdapter = AlbumListAdapter()


    init {
        loadAlbums()
    }

    fun loadAlbums() {
        subscription = Observable.fromCallable { albumDao.getAllAlbums }
            .concatMap { dbAlbumList ->
                if (dbAlbumList.isEmpty()) {
                    albumApi.getAlbums().concatMap { apiAlbumList ->
                        albumDao.insertAll(*apiAlbumList.toTypedArray())
                        Observable.just(albumDao.getAllAlbums)
                    }
                } else {
                    Observable.just(dbAlbumList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveAlbumListStart() }
            .doOnTerminate { onRetrieveAlbumListFinish() }
            .subscribe(
                { result -> onRetrieveAlbumListSuccess(result) },
                { onRetrieveAlbumListError() }
            )
    }

    fun onRetrieveAlbumListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun onRetrieveAlbumListFinish() {
        loadingVisibility.value = View.GONE
    }

    fun onRetrieveAlbumListSuccess(albumList: List<Album>) {
        albumListAdapter.updateAlbumList(albumList)
    }

    fun onRetrieveAlbumListError() {
        errorMessage.value = R.string.album_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}