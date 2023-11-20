package com.fabian.mobile.shophouseapp.producto

import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.marca.Marca
import com.fabian.mobile.shophouseapp.producto.network.model.ProductoResponse
import com.fabian.mobile.shophouseapp.producto.network.servicios.ProductoClient

class ProductoRepositorio(private val api: ProductoClient) {

    suspend fun getProductos(): Result<List<Producto>> {
        return try {
            val response = api.getProductos()
            val result = response.map {
                map(productoResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductosBuscarPorNombre(nombre:String): Result<List<Producto>> {
        return try {
            val response = api.getProductosBuscarPorNombre(nombre = nombre)
            val result = response.map {
                map(productoResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductosBuscarPorCategoria(categoriaId:Int): Result<List<Producto>> {
        return try {
            val response = api.getProductosBuscarPorCategoria(categoriaId = categoriaId)
            val result = response.map {
                map(productoResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductosBuscarPorCategoriaYNombre(categoriaId:Int,nombre:String): Result<List<Producto>> {
        return try {
            val response = api.getProductosBuscarPorCategoriaYNombre(categoriaId = categoriaId, nombre = nombre)
            val result = response.map {
                map(productoResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun map(productoResponse: ProductoResponse): Producto {
        return Producto(
            id = productoResponse.id,
            marca = Marca(
                id = productoResponse.marca.id, nombre = productoResponse.marca.nombre
            ),
            categoria = Categoria(
                id = productoResponse.categoria.id,
                nombre = productoResponse.categoria.nombre,
                imagen = productoResponse.categoria.imagen
            ),
            imagen = productoResponse.imagen,
            nombre = productoResponse.nombre,
            codigoBarras = productoResponse.codigoBarras,
            precioVenta = productoResponse.precioVenta,
            precioCompra = productoResponse.precioCompra
        )
    }
}