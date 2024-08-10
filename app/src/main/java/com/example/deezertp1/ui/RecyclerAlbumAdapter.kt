package com.example.deezertp1.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezertp1.databinding.RecyclerviewItemAlbumBinding
import com.example.deezertp1.service.data.Album

class RecyclerAlbumAdapter(private val albums: List<Album> = emptyList()) :
    RecyclerView.Adapter<RecyclerAlbumAdapter.AlbumViewHolder>() {
    companion object {
        private const val TAG = "RecyclerAlbumAdapter"
    }
    private var onClickListener: OnClickListener? = null


    init {
        Log.d(TAG, "RecyclerAlbumAdapter listAlbum : ${albums.size}")
    }

    inner class AlbumViewHolder(val binding: RecyclerviewItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemCount(): Int = albums.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        val binding = RecyclerviewItemAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder position=${position}")

        val album: Album = albums[position]
        Log.d(TAG, "album ${album.cover_medium}")

        try {
            Glide.with(holder.binding.root.context)
                .load(album.cover_medium)
                .into(holder.binding.imageViewAlbum)
        } catch (e: Throwable) {
            Log.e(TAG, "Glide", e)
        }
        holder.binding.textViewAlbum1.text = album.cover
        holder.binding.textViewAlbum2.text =
            "${album.fans} fans"

        holder.binding.imageViewAlbum.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, album)
            }
        }
    }

    // A function to bind the onclickListener
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Album)
    }
}