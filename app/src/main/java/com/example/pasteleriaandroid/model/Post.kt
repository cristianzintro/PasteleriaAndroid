package com.example.pasteleriaandroid.data.model

// Coincide con los campos de https://jsonplaceholder.typicode.com/posts
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
