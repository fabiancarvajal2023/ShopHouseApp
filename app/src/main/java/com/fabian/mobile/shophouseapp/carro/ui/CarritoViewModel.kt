package com.fabian.mobile.shophouseapp.carro.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabian.mobile.shophouseapp.carro.CarritoRepositorio
import com.fabian.mobile.shophouseapp.carro.ProductoCarrito
import com.fabian.mobile.shophouseapp.carro.ProductoVenta
import com.fabian.mobile.shophouseapp.carro.Venta
import com.fabian.mobile.shophouseapp.carro.network.servicios.CarritoClient
import com.fabian.mobile.shophouseapp.producto.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class CarritoViewModel(
    private val carritoRepositorio: CarritoRepositorio = CarritoRepositorio(api = CarritoClient.instance)
) : ViewModel() {

    private val _showSuccessDialog = MutableStateFlow(false)
    val showSuccessDialog: StateFlow<Boolean> = _showSuccessDialog.asStateFlow()

    private val _productosCarrito = MutableStateFlow(emptyList<ProductoCarrito>())
    val productosCarrito: StateFlow<List<ProductoCarrito>> = _productosCarrito.asStateFlow()

    private val _total = MutableStateFlow(-1)
    val total: StateFlow<Int> = _total.asStateFlow()

    private val _numeroProductos = MutableStateFlow(-1)
    val numeroProductos: StateFlow<Int> = _numeroProductos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _login = MutableStateFlow(false)
    val login: StateFlow<Boolean> = _login.asStateFlow()

    private var id: Long = -1

    fun addProducto(producto: Producto) {
        var existProduct = false
        val newList = mutableListOf<ProductoCarrito>()
        for (pc in _productosCarrito.value) {
            if (pc.producto.id == producto.id) {
                existProduct = true
                newList.add(ProductoCarrito(pc.producto, (pc.cantidad + 1)))
            } else {
                newList.add(ProductoCarrito(pc.producto, pc.cantidad))
            }
        }

        if (!existProduct) {
            newList.add(ProductoCarrito(producto, 1))
        }
        _productosCarrito.value = newList
    }

    fun cargarValues() {
        var t = 0
        for (pc in _productosCarrito.value) {
            t += (pc.producto.precioVenta * pc.cantidad)
        }
        _numeroProductos.value = _productosCarrito.value.size
        _total.value = t
    }

    fun aumentar(id: Long) {
        val newList = mutableListOf<ProductoCarrito>()
        for (pc in _productosCarrito.value) {
            if (pc.producto.id == id) {
                newList.add(ProductoCarrito(pc.producto, (pc.cantidad + 1)))
            } else {
                newList.add(ProductoCarrito(pc.producto, pc.cantidad))
            }
        }
        _productosCarrito.value = newList
        cargarValues()
    }

    fun disminuir(id: Long) {
        val newList = mutableListOf<ProductoCarrito>()
        for (pc in _productosCarrito.value) {
            if (pc.producto.id == id) {
                if (pc.cantidad > 1) {
                    newList.add(ProductoCarrito(pc.producto, (pc.cantidad - 1)))
                }
            } else {
                newList.add(ProductoCarrito(pc.producto, pc.cantidad))
            }
        }
        _productosCarrito.value = newList
        cargarValues()
    }

    fun hacerPedido() {
        if (id > 0) {
            val productoVenta = _productosCarrito.value.map {
                ProductoVenta(
                    producto = it.producto.id,
                    cantidad = it.cantidad,
                    precioVenta = (it.producto.precioVenta * it.cantidad),
                    precioCompra = (it.producto.precioCompra * it.cantidad)
                )
            }
            val venta = Venta(
                fecha = Calendar.getInstance().timeInMillis,
                cliente = id,
                ventaTotal = _total.value,
                ventaTotalCompra = 0,
                productoVenta = productoVenta
            )
            viewModelScope.launch {
                _isLoading.value = true
                carritoRepositorio.registrar(
                    venta = venta
                )
                    .onSuccess {
                        _isLoading.value = false
                        if (it) {
                            _productosCarrito.value = emptyList()
                            cargarValues()
                            _showSuccessDialog.value = true
                        } else {

                        }
                    }.onFailure {
                        println()
                        _isLoading.value = false
                    }
            }
        } else {
            _login.value = true
        }
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun resetLogin() {
        _login.value = false
    }

    fun dismissSuccessDialog() {
        _showSuccessDialog.value = false
    }
}