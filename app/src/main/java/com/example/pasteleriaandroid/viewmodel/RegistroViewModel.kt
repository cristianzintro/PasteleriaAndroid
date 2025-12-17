package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.ClienteEntity
import com.example.pasteleriaandroid.data.room.DatabaseModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegistroState(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val aceptoTerminos: Boolean = false,
    val errores: Map<String, String> = emptyMap(),
) {
    val esValido: Boolean
        get() = nombre.isNotBlank()
                && email.contains("@")
                && telefono.length >= 8
                && aceptoTerminos
                && errores.isEmpty()
}

class RegistroViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DatabaseModule.getDatabase(app).clienteDao()

    private val _state = MutableStateFlow(RegistroState())
    val state: StateFlow<RegistroState> = _state

    fun onNombre(v: String) { _state.value = _state.value.copy(nombre = v) }
    fun onEmail(v: String) { _state.value = _state.value.copy(email = v) }
    fun onTelefono(v: String) { _state.value = _state.value.copy(telefono = v) }
    fun onTerminos(v: Boolean) { _state.value = _state.value.copy(aceptoTerminos = v) }

    fun enviar(onSuccess: (clienteId: Int) -> Unit) {
        val s = _state.value

        val errores = mutableMapOf<String, String>()
        if (s.nombre.isBlank()) errores["nombre"] = "Nombre requerido"
        if (!s.email.contains("@")) errores["email"] = "Email inválido"
        if (s.telefono.length < 8) errores["telefono"] = "Teléfono inválido"

        _state.value = s.copy(errores = errores)
        if (errores.isNotEmpty()) return

        viewModelScope.launch {
            val idLong = dao.insert(
                ClienteEntity(
                    nombre = s.nombre.trim(),
                    email = s.email.trim(),
                    telefono = s.telefono.trim()
                )
            )
            onSuccess(idLong.toInt())
        }
    }
}
