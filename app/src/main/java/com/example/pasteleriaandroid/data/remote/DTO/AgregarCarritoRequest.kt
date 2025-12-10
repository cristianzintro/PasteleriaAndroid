package com.example.pasteleriaandroid.data.remote.dto

data class AgregarCarritoRequest(
    val productoId: Int,
    val cantidad: Int,
    val usuarioId: Int      // o cámbialo a emailUsuario: String si tu backend usa correo
)
