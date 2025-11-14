package com.example.pasteleriaandroid.data.remote

import com.example.pasteleriaandroid.data.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}
