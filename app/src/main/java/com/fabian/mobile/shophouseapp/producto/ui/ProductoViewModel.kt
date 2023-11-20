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

    private val _productos = MutableStateFlow(emptyList<Producto>())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()


    fun cargarProductos(catId: Int?) {
        viewModelScope.launch {
            if (catId != null) {
                productoRepositorio.getProductosBuscarPorCategoria(categoriaId = catId)
                    .onSuccess {
                        _productos.value = it
                    }.onFailure {
                        println()
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

}