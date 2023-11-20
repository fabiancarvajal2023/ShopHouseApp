package com.fabian.mobile.shophouseapp.categoria

import com.fabian.mobile.shophouseapp.categoria.network.model.PromocionResponse
import com.fabian.mobile.shophouseapp.categoria.network.servicios.PromocionClient

class PromocionRepositorio(private val api: PromocionClient) {

    suspend fun getCategorias(): Result<List<Promocion>> {
        return try {
            val response = api.getPromociones()
            val result = response.map {
                map(promocionResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun map(promocionResponse: PromocionResponse): Promocion {
        return Promocion(
            id = promocionResponse.id,
            categoria = Categoria(
                id = promocionResponse.categoria.id,
                nombre = promocionResponse.categoria.nombre,
                imagen = promocionResponse.categoria.imagen
            ),
            porcentaje = promocionResponse.porcentaje,
            articuloRequerido = promocionResponse.articuloRequerido
        )
    }
}