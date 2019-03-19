package com.example.albumsmvvm.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.albumsmvvm.R
import com.example.albumsmvvm.databinding.ItemAlbumBinding
import com.example.albumsmvvm.model.Album

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>() {

    private lateinit var albumList: List<Album>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding: ItemAlbumBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_album, parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::albumList.isInitialized) albumList.size else 0
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albumList[position])
    }

    fun updateAlbumList(albumList: List<Album>) {
        this.albumList = albumList
        notifyDataSetChanged()
    }


    class AlbumViewHolder(private val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AlbumViewModel()

        fun bind(album: Album) {
            viewModel.bind(album)
            binding.viewModel = viewModel
        }
    }
}