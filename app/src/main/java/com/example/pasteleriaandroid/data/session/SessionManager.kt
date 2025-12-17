package com.example.pasteleriaandroid.data.session

object SessionManager {
    private var clienteId: Int? = null

    fun setClienteId(id: Int) {
        clienteId = id
    }

    fun getClienteId(): Int? = clienteId

    fun clear() {
        clienteId = null
    }
}
