package com.example.pasteleriaandroid.model

data class Producto(
    val id: Int,
    val codigo: String?,       // puede venir null
    val nombre: String,
    val categoria: String?,
    val descripcion: String?,
    val precio: Int,
    val imagen: String,        // URL RAW de GitHub que llega desde el backend
    val stock: Int? = null     // opcional, por si el backend lo envía
)
