package com.fabian.mobile.shophouseapp.categoria

import com.fabian.mobile.shophouseapp.categoria.network.model.CategoriaResponse
import com.fabian.mobile.shophouseapp.categoria.network.servicios.CategoriaClient

class CategoriaRepositorio(private val api: CategoriaClient) {

    suspend fun getCategorias(): Result<List<Categoria>> {
        return try {
            val response = api.getCategoria()
            val result = response.map {
                map(categoriaResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getCategoriasPorNombre(nombre: String): Result<List<Categoria>> {
        return try {
            val response = api.getCategoriaPorNombre(nombre = nombre)
            val result = response.map {
                map(categoriaResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun map(categoriaResponse: CategoriaResponse): Categoria {
        return Categoria(
            id = categoriaResponse.id,
            nombre = categoriaResponse.nombre,
            imagen = categoriaResponse.imagen
        )
    }
}