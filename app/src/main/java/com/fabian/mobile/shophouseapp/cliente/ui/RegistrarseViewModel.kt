package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.cliente.Cliente
import com.fabian.mobile.shophouseapp.cliente.ClienteRepositorio
import com.fabian.mobile.shophouseapp.cliente.Tipoidentificacion
import com.fabian.mobile.shophouseapp.cliente.TipoidentificacionRepositorio
import com.fabian.mobile.shophouseapp.cliente.network.servicios.ClienteClient
import com.fabian.mobile.shophouseapp.cliente.network.servicios.TipoIdentificacionClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrarseViewModel(
    private val clienteRepositorio: ClienteRepositorio = ClienteRepositorio(
        ClienteClient.instance
    ),
    private val tipoidentificacionRepositorio: TipoidentificacionRepositorio = TipoidentificacionRepositorio(
        TipoIdentificacionClient.instance
    ),
) : ViewModel() {

    private val _showSuccessDialog = MutableStateFlow(false)
    val showSuccessDialog: StateFlow<Boolean> = _showSuccessDialog.asStateFlow()

    private val _tiposidentificacion = MutableStateFlow(emptyList<Tipoidentificacion>())
    val tiposidentificacion: StateFlow<List<Tipoidentificacion>> =
        _tiposidentificacion.asStateFlow()

    private val _seleccionado = MutableStateFlow("")
    val seleccionado: StateFlow<String> = _seleccionado.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _identificacion = MutableStateFlow("")
    val identificacion: StateFlow<String> = _identificacion.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    var tipoidentificacionSeleccionado: Tipoidentificacion? = null

    private val _enabledButton = MutableStateFlow(false)
    val enabledButton: StateFlow<Boolean> = _enabledButton.asStateFlow()

    fun cargarTiposidentificacion() {
        viewModelScope.launch {
            _loading.value = true
            tipoidentificacionRepositorio.getTiposIdentificacion()
                .onSuccess {
                    _tiposidentificacion.value = it
                    _loading.value = false
                }.onFailure {
                    println()
                    _loading.value = false
                }
        }
    }

    fun onValueChange(value: String) {
        _seleccionado.value = value
    }

    fun onClick(value: Tipoidentificacion) {
        _seleccionado.value = value.nombre
        tipoidentificacionSeleccionado = value
        _enabledButton.value = enabledButton()
    }

    fun identificacionChange(value: String) {
        _identificacion.value = value
        _enabledButton.value = enabledButton()
    }

    fun nombreChange(value: String) {
        _nombre.value = value
        _enabledButton.value = enabledButton()
    }

    fun emailChange(value: String) {
        _email.value = value
        _enabledButton.value = enabledButton()
    }

    fun passwordChange(value: String) {
        _password.value = value
        _enabledButton.value = enabledButton()
    }

    private fun enabledButton(): Boolean {
        return validateTipoIdentificacion(_seleccionado.value) && validateIdentificacion(
            _identificacion.value
        ) && validateNombre(_nombre.value) &&
                validateEmail(_email.value) && validatePassword(_password.value)
    }


    private fun validateTipoIdentificacion(tipo: String): Boolean {
        return tipo.isNotEmpty()
    }

    private fun validateNombre(nombre: String): Boolean {
        return nombre.isNotEmpty() && nombre.length >= 2
    }

    private fun validateIdentificacion(identificacion: String): Boolean {
        return identificacion.isNotEmpty()
    }

    private fun validateEmail(email: String): Boolean {

        val mPattern =
            Regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        return email.isNotEmpty() && mPattern.matches(email)

    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 5
    }

    fun dismissSuccessDialog() {
        _showSuccessDialog.value = false
    }

    fun registrar() {
        viewModelScope.launch {
            _loading.value = true
            clienteRepositorio.registrar(
                cliente = Cliente(
                    tipoIdentificacion = tipoidentificacionSeleccionado!!.id,
                    identificacion = _identificacion.value,
                    nombre = _nombre.value,
                    email = _email.value,
                    password = _password.value
                )
            )
                .onSuccess {
                    resetValues()
                    _loading.value = false
                    _showSuccessDialog.value = true
                }.onFailure {
                    println()
                    _loading.value = false
                }
        }
    }

    private fun resetValues() {
        _seleccionado.value = ""
        tipoidentificacionSeleccionado = null
        _identificacion.value = ""
        _nombre.value = ""
        _email.value = ""
        _password.value = ""
        _enabledButton.value = false
    }

}