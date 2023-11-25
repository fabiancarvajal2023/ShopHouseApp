package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.cliente.ClienteRepositorio
import com.fabian.mobile.shophouseapp.cliente.Login
import com.fabian.mobile.shophouseapp.cliente.network.servicios.ClienteClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IniciarSesionViewModel(
    private val clienteRepositorio: ClienteRepositorio = ClienteRepositorio(
        ClienteClient.instance
    )
) : ViewModel() {

    private val _showErrorDialog = MutableStateFlow(false)
    val showErrorDialog: StateFlow<Boolean> = _showErrorDialog.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _id = MutableStateFlow(-1L)
    val id: StateFlow<Long> = _id.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _enabledButton = MutableStateFlow(false)
    val enabledButton: StateFlow<Boolean> = _enabledButton.asStateFlow()

    fun emailChange(value: String) {
        _email.value = value
        _enabledButton.value = enabledButton()
    }

    fun passwordChange(value: String) {
        _password.value = value
        _enabledButton.value = enabledButton()
    }

    private fun enabledButton(): Boolean {
        return validateEmail(_email.value) && validatePassword(_password.value)
    }


    private fun validateEmail(email: String): Boolean {

        val mPattern =
            Regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        return email.isNotEmpty() && mPattern.matches(email)

    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 5
    }

    fun iniciarSesion() {
        viewModelScope.launch {
            _isLoading.value = true
            clienteRepositorio.login(
                login = Login(
                    email = _email.value,
                    password = _password.value
                )
            )
                .onSuccess {
                    _isLoading.value = false
                    if (it.nombre.isNotEmpty()) {
                        resetValues()
                        _id.value = it.id
                        _nombre.value = it.nombre
                    } else {
                        _showErrorDialog.value = true
                    }
                }.onFailure {
                    println()
                    _isLoading.value = false
                }
        }
    }

    private fun resetValues() {
        _email.value = ""
        _password.value = ""
        _enabledButton.value = false
    }

    fun resetNombre() {
        _nombre.value = ""
    }

    fun resetId() {
        _id.value = -1L
    }

    fun dismissErrorDialog() {
        _showErrorDialog.value = false
    }

}