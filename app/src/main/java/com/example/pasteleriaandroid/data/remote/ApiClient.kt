package com.example.pasteleriaandroid.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // Base URL: Nota 💡
    // Tu backend expone /api/productos y /api/carrito
    // En tu ApiService usas @GET("productos"), así que la base debe ser .../api/
    private const val BASE_URL = "http://10.0.2.2:8081/api/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
