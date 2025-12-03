package com.example.aplicacionprueba.Modelo.repository
import com.example.aplicacionprueba.Modelo.GeneralResponse
import com.example.aplicacionprueba.Modelo.Loan
import com.example.aplicacionprueba.Modelo.data.LoanRequest
import com.example.aplicacionprueba.Modelo.network.PrestamosApi
import com.example.aplicacionprueba.Modelo.network.RetrofitClient
class LoanRepository(
    private val api: PrestamosApi = RetrofitClient.instance
) {
    /**
     * Solicita un nuevo préstamo llamando a 'prestamos/solicitar.php'.
     */
    suspend fun requestLoan(request: LoanRequest): Result<GeneralResponse> {
        return try {
            val response = api.requestLoan(request)
            // Lógica de validación similar a Login/Register
            if (response.isSuccessful) {
                Result.success(response.body() ?: GeneralResponse(false, "Respuesta nula."))
            } else {
                Result.failure(Exception("Error al solicitar el préstamo. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene el historial de préstamos para el usuario logueado.
     */
    suspend fun getUserHistory(userId: Int): Result<List<Loan>> {
        return try {
            val response = api.getUserLoanHistory(userId)
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error al cargar el historial. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}