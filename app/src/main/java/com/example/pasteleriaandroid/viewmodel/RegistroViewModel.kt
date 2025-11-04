package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.ClienteEntity
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.repository.ClienteRepository
import com.example.pasteleriaandroid.validation.Validators
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistroState(
    val nombre: String = "",
    val email: String = "",
    val telefono: String = "",
    val aceptoTerminos: Boolean = false,
    val errores: Map<String, String> = emptyMap(),
) {
    val esValido: Boolean =
        Validators.nombreValido(nombre) &&
                Validators.emailValido(email) &&
                Validators.telefonoValido(telefono) &&
                aceptoTerminos
}

class RegistroViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val repo = ClienteRepository(db.clienteDao())

    private val _state = MutableStateFlow(RegistroState())
    val state: StateFlow<RegistroState> = _state

    fun onNombre(v: String) = _state.update {
        it.copy(nombre = v, errores = it.errores - "nombre")
    }

    fun onEmail(v: String) = _state.update {
        it.copy(email = v, errores = it.errores - "email")
    }

    fun onTelefono(v: String) = _state.update {
        it.copy(telefono = v, errores = it.errores - "telefono")
    }

    fun onTerminos(v: Boolean) = _state.update { it.copy(aceptoTerminos = v) }

    private fun validarCampos(): Map<String, String> {
        val e = mutableMapOf<String, String>()
        val s = _state.value
        if (!Validators.nombreValido(s.nombre)) e["nombre"] = "Mínimo 3 caracteres."
        if (!Validators.emailValido(s.email)) e["email"] = "Correo no válido."
        if (!Validators.telefonoValido(s.telefono)) e["telefono"] = "Sólo dígitos (8 a 15)."
        return e
    }

    /**
     * onSuccess se llama solo si está todo válido.
     */
    fun enviar(onSuccess: () -> Unit) {
        val errores = validarCampos()
        if (errores.isNotEmpty()) {
            _state.update { it.copy(errores = errores) }
            return
        }

        // aquí ya está válido
        val s = _state.value
        viewModelScope.launch {
            repo.insertarCliente(
                ClienteEntity(
                    nombre = s.nombre,
                    email = s.email,
                    telefono = s.telefono
                )
            )
            onSuccess()
            // opcional: limpiar
            _state.value = RegistroState()
        }
    }
}
