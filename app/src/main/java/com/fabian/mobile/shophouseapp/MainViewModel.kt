package com.fabian.mobile.shophouseapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {

    private val _currentScreen = MutableStateFlow(ScreensEnum.Splash)
    val currentScreen: StateFlow<ScreensEnum> = _currentScreen.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _mostrarBotonAtras = MutableStateFlow(false)
    val mostrarBotonAtras: StateFlow<Boolean> = _mostrarBotonAtras.asStateFlow()

    private val _mostrarCajaBusqueda = MutableStateFlow(false)
    val mostrarCajaBusqueda: StateFlow<Boolean> = _mostrarCajaBusqueda.asStateFlow()

    private val _mostrarCarroCompras = MutableStateFlow(false)
    val mostrarCarroCompras: StateFlow<Boolean> = _mostrarCarroCompras.asStateFlow()

    private val _mostrarRegistrarUsuario = MutableStateFlow(false)
    val mostrarRegistrarUsuario: StateFlow<Boolean> = _mostrarRegistrarUsuario.asStateFlow()

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setMostrarBotonAtras(value: Boolean) {
        _mostrarBotonAtras.value = value
    }

    fun setMostrarCajaBusqueda(value: Boolean) {
        _mostrarCajaBusqueda.value = value
    }

    fun setMostrarCarroCompras(value: Boolean) {
        _mostrarCarroCompras.value = value
    }

    fun setMostrarRegistrarUsuario(value: Boolean) {
        _mostrarRegistrarUsuario.value = value
    }

    fun changeUserName(value: String) {
        _userName.value = value
    }

    fun changeScreen(value: ScreensEnum) {
        _currentScreen.value = value
    }
}