package com.fabian.mobile.shophouseapp.cliente

import com.fabian.mobile.shophouseapp.cliente.network.model.TipoIdentificacionResponse
import com.fabian.mobile.shophouseapp.cliente.network.servicios.TipoIdentificacionClient

class TipoidentificacionRepositorio(private val api: TipoIdentificacionClient) {

    suspend fun getTiposIdentificacion(): Result<List<Tipoidentificacion>> {
        return try {
            val response = api.getTiposIdentificacion()
            val result = response.map {
                map(tipoIdentificacionResponse = it)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun map(tipoIdentificacionResponse: TipoIdentificacionResponse): Tipoidentificacion {
        return Tipoidentificacion(
            id = tipoIdentificacionResponse.id,
            nombre = tipoIdentificacionResponse.nombre,
            abreviatura = tipoIdentificacionResponse.abreviatura
        )
    }
}