package com.fabian.mobile.shophouseapp.categoria.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.categoria.CategoriaRepositorio
import com.fabian.mobile.shophouseapp.categoria.network.servicios.CategoriaClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriaViewModel(
    private val categoriaRepositorio: CategoriaRepositorio = CategoriaRepositorio(
        CategoriaClient.instance
    )
) : ViewModel() {


    private val _categorias = MutableStateFlow(emptyList<Categoria>())
    val categorias: StateFlow<List<Categoria>> = _categorias.asStateFlow()

    fun loadCategorias() {
        viewModelScope.launch {
            categoriaRepositorio.getCategorias()
                .onSuccess {
                    _categorias.value = it
                }.onFailure {
                    println()
                }
        }
    }
}