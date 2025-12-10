package com.example.pasteleriaandroid.data.remote

import com.example.pasteleriaandroid.data.remote.dto.AgregarCarritoRequest
import com.example.pasteleriaandroid.model.Producto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // =============== PRODUCTOS ===============
    @GET("productos")
    suspend fun getProductos(): List<Producto>

    @GET("productos/{id}")
    suspend fun getProductoById(
        @Path("id") id: Int
    ): Producto

    // =============== CARRITO ===============
    @POST("carrito/agregar")
    suspend fun agregarAlCarrito(
        @Body request: AgregarCarritoRequest
    ): MensajeResponse
}
