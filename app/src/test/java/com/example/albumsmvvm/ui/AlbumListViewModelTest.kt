package com.example.albumsmvvm.ui

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albumsmvvm.R
import com.example.albumsmvvm.model.AlbumDao
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AlbumListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var albumDao: AlbumDao

    private lateinit var albumListViewModel: AlbumListViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        albumListViewModel = AlbumListViewModel(albumDao)
    }

    @Test
    fun onRetrieveAlbumListStartTest() {
        albumListViewModel.onRetrieveAlbumListStart()
        Assert.assertEquals(View.VISIBLE, albumListViewModel.loadingVisibility.value)
        Assert.assertNull(albumListViewModel.errorMessage.value)
    }

    @Test
    fun onRetrieveAlbumListFinishTest() {
        albumListViewModel.onRetrieveAlbumListFinish()
        Assert.assertEquals(View.GONE, albumListViewModel.loadingVisibility.value)
    }

    @Test
    fun onRetrieveAlbumListErrorTest() {
        albumListViewModel.onRetrieveAlbumListError()
        Assert.assertEquals(albumListViewModel.errorMessage.value, R.string.album_error)
    }

}