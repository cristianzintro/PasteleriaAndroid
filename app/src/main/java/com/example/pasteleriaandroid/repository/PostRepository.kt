package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.model.Post
import com.example.pasteleriaandroid.data.remote.RetrofitInstance

class PostRepository {

    suspend fun getPosts(): List<Post> {
        // Aquí podrías manejar try/catch, cache, etc.
        return RetrofitInstance.api.getPosts()
    }
}
