package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.room.ClienteDao
import com.example.pasteleriaandroid.data.room.ClienteEntity

class ClienteRepository(private val dao: ClienteDao) {

    fun getClientes() = dao.getAll()

    suspend fun insertarCliente(cliente: ClienteEntity) {
        dao.insert(cliente)
    }
}
