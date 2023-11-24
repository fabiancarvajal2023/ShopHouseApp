package com.fabian.mobile.shophouseapp.cliente

import com.fabian.mobile.shophouseapp.cliente.network.model.ClienteRequest
import com.fabian.mobile.shophouseapp.cliente.network.model.LoginRequest
import com.fabian.mobile.shophouseapp.cliente.network.model.LoginResponse
import com.fabian.mobile.shophouseapp.cliente.network.servicios.ClienteClient

class ClienteRepositorio(private val api: ClienteClient) {

    suspend fun registrar(cliente: Cliente): Result<Long> {
        return try {
            val response = api.registrar(map(cliente))
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(login: Login): Result<LoginResponse> {
        return try {
            val response = api.login(map(login))
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun map(cliente: Cliente): ClienteRequest {
        return ClienteRequest(
            tipoIdentificacion = cliente.tipoIdentificacion,
            identificacion = cliente.identificacion,
            nombre = cliente.nombre,
            email = cliente.email,
            password = cliente.password,
            direccion = cliente.direccion,
            telefono = cliente.telefono
        )
    }

    fun map(login: Login): LoginRequest {
        return LoginRequest(
            email = login.email,
            password = login.password
        )
    }
}