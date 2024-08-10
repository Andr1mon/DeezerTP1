package com.example.deezertp1.service.data

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

abstract class CallbackSearchAlbums: Callback {

    companion object {
        val TAG = "CallbackSearchAlbums"
    }

    abstract fun fireDataSearchAlbums(data:DataSearchAlbums)

    override fun onFailure(call: Call, e: IOException) {
        Log.i(TAG, "onFailure: ", e)
    }

    override fun onResponse(call: Call, response: Response) {
        Log.d(TAG, "onResponse: ")
        val responseData = response.body!!.string()
        Log.d(TAG, "responseData: $responseData")
        val gson = Gson()
        val data: DataSearchAlbums =
            gson.fromJson(responseData, DataSearchAlbums::class.java)

        Log.d(TAG, "data $data")

        fireDataSearchAlbums(data)
    }
}