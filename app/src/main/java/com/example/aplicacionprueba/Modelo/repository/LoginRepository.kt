package com.example.aplicacionprueba.Modelo.repository

import com.example.aplicacionprueba.Modelo.data.LoginRequest
import com.example.aplicacionprueba.Modelo.data.LoginResponse
import com.example.aplicacionprueba.Modelo.network.PrestamosApi
import com.example.aplicacionprueba.Modelo.network.RetrofitClient
class LoginRepository(private val api: PrestamosApi = RetrofitClient.instance
) {
    suspend fun attemptLogin(correo: String, contrasena: String): Result<LoginResponse> {
        return try {
            // 1. Crear el objeto LoginRequest
            val request = LoginRequest(correo, contrasena)

            // 2. Llamar a la API con el objeto completo (¡Ahora coincide con PrestamosApi!)
            val response = api.login(request)

            if (response.isSuccessful) {
                // ... (El resto del manejo de la respuesta es correcto)
                val loginResponse = response.body()

                if (loginResponse != null && loginResponse.success) {
                    Result.success(loginResponse)
                } else if (loginResponse != null) {
                    Result.failure(Exception(loginResponse.message))
                } else {
                    Result.failure(Exception("Respuesta del servidor vacía."))
                }
            } else {
                Result.failure(Exception("Error en la conexión con el servidor. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}