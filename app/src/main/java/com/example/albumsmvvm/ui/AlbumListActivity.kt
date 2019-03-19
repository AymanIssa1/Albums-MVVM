package com.example.albumsmvvm.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsmvvm.R
import com.example.albumsmvvm.databinding.ActivityAlbumListBinding
import com.example.albumsmvvm.di.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class AlbumListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumListBinding
    private lateinit var viewModel: AlbumListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        binding.albumList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(AlbumListViewModel::class.java)
        viewModel.errorMessage.observe(
            this,
            Observer { errorMessage -> if (errorMessage != null) showError(errorMessage) else hideError() })
        binding.viewModel = viewModel

    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() = errorSnackbar?.dismiss()


}