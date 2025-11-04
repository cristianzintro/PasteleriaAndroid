package com.example.pasteleriaandroid.validation

object Validators {
    fun nombreValido(v: String) = v.trim().length >= 3
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    fun emailValido(v: String) = EMAIL_REGEX.matches(v.trim())
    fun telefonoValido(v: String) = v.trim().length in 8..15 && v.all { it.isDigit() }
}
