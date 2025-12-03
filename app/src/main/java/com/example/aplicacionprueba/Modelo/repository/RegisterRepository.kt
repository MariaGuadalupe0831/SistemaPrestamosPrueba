package com.example.aplicacionprueba.Modelo.repository
import com.example.aplicacionprueba.Modelo.data.RegisterRequest
import com.example.aplicacionprueba.Modelo.GeneralResponse
import com.example.aplicacionprueba.Modelo.network.PrestamosApi
import com.example.aplicacionprueba.Modelo.network.RetrofitClient
class RegisterRepository (
    // Inyección de la interfaz de la API. Por defecto, usa el cliente Singleton.
    private val api: PrestamosApi = RetrofitClient.instance
) {
    /**
     * Intenta registrar un nuevo usuario llamando al endpoint 'register.php'.
     * @param request Objeto RegisterRequest que contiene los datos del formulario.
     * @return Result<GeneralResponse> que indica éxito o fallo de la operación.
     */
    suspend fun attemptRegister(request: RegisterRequest): Result<GeneralResponse> {
        return try {
            // Llamada asíncrona a la API (POST con el JSON del RegisterRequest)
            val response = api.register(request)

            if (response.isSuccessful) {
                // La respuesta HTTP fue 2xx
                val registerResponse = response.body()

                if (registerResponse != null && registerResponse.success) {
                    // El servidor respondió success: true
                    Result.success(registerResponse)
                } else if (registerResponse != null) {
                    // El servidor respondió success: false (ej: el correo ya existe)
                    Result.failure(Exception(registerResponse.message))
                } else {
                    // Respuesta vacía
                    Result.failure(Exception("Respuesta del servidor vacía."))
                }
            } else {
                // Respuesta HTTP no exitosa (ej: 404, 500)
                Result.failure(Exception("Error en la conexión con el servidor. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            // Error de red (sin internet, timeout, etc.)
            Result.failure(e)
        }
    }
}