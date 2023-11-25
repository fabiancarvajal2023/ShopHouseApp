package com.fabian.mobile.shophouseapp.carro

import com.fabian.mobile.shophouseapp.carro.network.model.ProductoVentaRequest
import com.fabian.mobile.shophouseapp.carro.network.model.VentaRequest
import com.fabian.mobile.shophouseapp.carro.network.servicios.CarritoClient

class CarritoRepositorio(private val api: CarritoClient) {

    suspend fun registrar(venta: Venta): Result<Boolean> {
        return try {
            val response = api.registrar(map(venta))
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun map(venta: Venta): VentaRequest {
        return VentaRequest(
            fecha = venta.fecha,
            cliente = venta.cliente,
            ventaTotal = venta.ventaTotal,
            ventaTotalCompra = venta.ventaTotalCompra,
            productoVenta = venta.productoVenta.map {
                map(it)
            }
        )
    }

    fun map(productoVenta: ProductoVenta): ProductoVentaRequest {
        return ProductoVentaRequest(
            producto = productoVenta.producto,
            cantidad = productoVenta.cantidad,
            precioVenta = productoVenta.precioVenta,
            precioCompra = productoVenta.precioCompra
        )
    }
}