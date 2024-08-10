package com.example.deezertp1.service.data

data class DataSearchAlbums(
    val data: List<Album>,
    val next: String,
    val total: Int
)