package com.fabian.mobile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel() : ViewModel() {
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

    private val _isLogin = MutableStateFlow(false)
    val isLogin: StateFlow<Boolean> = _isLogin.asStateFlow()

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setMostrarBotonAtras(value: Boolean) {
        _mostrarBotonAtras.value = value
    }

    fun setMostrarCajaBusqueda(value: Boolean) {
        _mostrarCajaBusqueda.value = value
    }

    fun setMostrarCarroCompras(value:Boolean){
        _mostrarCarroCompras.value = value
    }

    fun setMostrarRegistrarUsuario(value:Boolean){
        _mostrarRegistrarUsuario.value = value
    }

    fun setIsLogin(value:Boolean){
        _isLogin.value = value
    }
}