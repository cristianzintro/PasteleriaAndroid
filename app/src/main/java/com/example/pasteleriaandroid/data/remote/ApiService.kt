package com.example.pasteleriaandroid.data.remote

import com.example.pasteleriaandroid.model.Producto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // GET http://10.0.2.2:8081/api/productos
    @GET("productos")
    suspend fun getProductos(): List<Producto>

    // GET http://10.0.2.2:8081/api/productos/{id}
    @GET("productos/{id}")
    suspend fun getProductoById(@Path("id") id: Int): Producto
}
