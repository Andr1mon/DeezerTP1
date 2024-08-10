package com.example.deezertp1.service.data

data class DataSearchAuthor(
    val data: List<Author>,
    val next: String,
    val total: Int
)