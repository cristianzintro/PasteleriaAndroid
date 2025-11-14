package com.example.pasteleriaandroid.validation

object Validators {

    // Nombre válido: mínimo 3 caracteres
    fun nombreValido(v: String) = v.trim().length >= 3

    // Email básico
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    fun emailValido(v: String) = EMAIL_REGEX.matches(v.trim())

    // País: solo letras y espacios, entre 3 y 30 caracteres
    private val PAIS_REGEX = Regex("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{3,30}$")
    fun paisValido(v: String) = PAIS_REGEX.matches(v.trim())

    // Teléfono: solo números, entre 8 y 15 dígitos
    fun telefonoValido(v: String) =
        v.trim().length in 8..15 && v.all { it.isDigit() }
}
