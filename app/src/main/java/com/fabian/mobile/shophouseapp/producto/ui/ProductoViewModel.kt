package com.fabian.mobile.shophouseapp.producto.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.producto.Producto
import com.fabian.mobile.shophouseapp.producto.ProductoRepositorio
import com.fabian.mobile.shophouseapp.producto.network.servicios.ProductoClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoViewModel(
    private val productoRepositorio: ProductoRepositorio = ProductoRepositorio(
        ProductoClient.instance
    ),
) : ViewModel() {

    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()

    private val _productos = MutableStateFlow(emptyList<Producto>())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private var categoriaId: Int? = null

    fun cargarProductos() {
        viewModelScope.launch {
            if (categoriaId != null) {
                if (_search.value.isNotEmpty() && _search.value.length >= 3) {
                    productoRepositorio.getProductosBuscarPorCategoriaYNombre(
                        categoriaId = categoriaId!!,
                        nombre = _search.value
                    )
                        .onSuccess {
                            _productos.value = it
                        }.onFailure {
                            println()
                        }
                } else {
                    productoRepositorio.getProductosBuscarPorCategoria(categoriaId = categoriaId!!)
                        .onSuccess {
                            _productos.value = it
                        }.onFailure {
                            println()
                        }
                }
            } else {
                productoRepositorio.getProductos()
                    .onSuccess {
                        _productos.value = it
                    }.onFailure {
                        println()
                    }
            }
        }
    }

    fun changeSearch(value: String) {
        _search.value = value
        cargarProductos()
    }

    fun setCategoriaId(catId: Int?) {
        categoriaId = catId
    }

}