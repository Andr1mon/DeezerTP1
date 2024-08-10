package com.example.deezertp1.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezertp1.databinding.RecyclerviewItemArtistBinding
import com.example.deezertp1.service.data.Author

class RecyclerAuthorAdapter(private val authors: List<Author> = emptyList()) :
    RecyclerView.Adapter<RecyclerAuthorAdapter.AuthorViewHolder>() {
    companion object {
        private const val TAG = "RecyclerAuthorAdapter"
    }
    private var onClickListener: OnClickListener? = null


    init {
        Log.d(TAG, "RecyclerAuthorAdapter listAuthor : ${authors.size}")
    }

    inner class AuthorViewHolder(val binding: RecyclerviewItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemCount(): Int = authors.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        Log.d(TAG, "onCreateViewHolder")

        val binding = RecyclerviewItemArtistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AuthorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder position=${position}")

        val author: Author = authors[position]
        Log.d(TAG, "author ${author.picture_medium}")

        try {
            Glide.with(holder.binding.root.context)
                .load(author.picture_medium)
                .into(holder.binding.imageViewAuthor)
        } catch (e: Throwable) {
            Log.e(TAG, "Glide", e)
        }
        holder.binding.textViewAuthor1.text = author.name
        holder.binding.textViewAuthor2.text =
            "${author.nb_fan} fans"

        holder.binding.imageViewAuthor.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, author)
            }
        }
    }

    // A function to bind the onclickListener
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: Author)
    }
}