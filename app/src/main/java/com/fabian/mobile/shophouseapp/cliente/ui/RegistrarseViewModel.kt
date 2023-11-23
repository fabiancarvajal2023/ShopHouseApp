package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.cliente.Tipoidentificacion
import com.fabian.mobile.shophouseapp.cliente.TipoidentificacionRepositorio
import com.fabian.mobile.shophouseapp.cliente.network.servicios.TipoIdentificacionClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrarseViewModel(
    private val tipoidentificacionRepositorio: TipoidentificacionRepositorio = TipoidentificacionRepositorio(
        TipoIdentificacionClient.instance
    ),
) : ViewModel() {

    private val _tiposidentificacion = MutableStateFlow(emptyList<Tipoidentificacion>())
    val tiposidentificacion: StateFlow<List<Tipoidentificacion>> =
        _tiposidentificacion.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


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

}