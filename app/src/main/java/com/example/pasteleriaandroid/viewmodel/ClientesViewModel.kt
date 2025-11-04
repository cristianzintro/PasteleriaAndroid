package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ClientesViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DatabaseModule.getDatabase(app).clienteDao()

    // lo exponemos como State< List<ClienteEntity> >
    val clientes = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
