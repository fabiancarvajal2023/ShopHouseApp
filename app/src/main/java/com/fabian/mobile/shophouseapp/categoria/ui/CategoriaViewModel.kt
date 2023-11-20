package com.fabian.mobile.shophouseapp.categoria.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.categoria.CategoriaRepositorio
import com.fabian.mobile.shophouseapp.categoria.Promocion
import com.fabian.mobile.shophouseapp.categoria.PromocionRepositorio
import com.fabian.mobile.shophouseapp.categoria.network.servicios.CategoriaClient
import com.fabian.mobile.shophouseapp.categoria.network.servicios.PromocionClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriaViewModel(
    private val categoriaRepositorio: CategoriaRepositorio = CategoriaRepositorio(
        CategoriaClient.instance
    ),
    private val promocionRepositorio: PromocionRepositorio = PromocionRepositorio(
        PromocionClient.instance
    )
) : ViewModel() {


    private val _categorias = MutableStateFlow(emptyList<Categoria>())
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    private val _promociones = MutableStateFlow(emptyList<Promocion>())
    val promociones: StateFlow<List<Promocion>> = _promociones.asStateFlow()


    fun cargarCategorias() {
        viewModelScope.launch {
            categoriaRepositorio.getCategorias()
                .onSuccess {
                    _categorias.value = it
                }.onFailure {
                    println()
                }
        }
    }

    fun cargarPromociones() {
        viewModelScope.launch {
            promocionRepositorio.getCategorias()
                .onSuccess {
                    _promociones.value = it
                }.onFailure {
                    println()
                }
        }
    }
}