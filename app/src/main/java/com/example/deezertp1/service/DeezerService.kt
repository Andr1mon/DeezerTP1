package com.example.deezertp1.service

import android.util.Log
import com.example.deezertp1.service.data.CallbackSearchAlbums
import com.example.deezertp1.service.data.CallbackSearchAuthor
import okhttp3.OkHttpClient
import okhttp3.Request



class DeezerService {
    companion object {
        val TAG = "DeezerService"
        var client = OkHttpClient()
    }
    
    fun searchAuthor(name: String, callback: CallbackSearchAuthor) {
        Log.d(TAG, "searchAuthor: $name")

        val request: Request = Request.Builder()
            .url("https://api.deezer.com/search/artist?q=$name")
            .build()

        client.newCall(request).enqueue(callback)

    }

    fun searchAlbumsOfAuthor(id: String, callback: CallbackSearchAlbums) {
        Log.d(TAG, "searchAlbums: $id")

        val request: Request = Request.Builder()
            .url("https://api.deezer.com/artist/$id/albums")
            .build()

        client.newCall(request).enqueue(callback)

    }
}