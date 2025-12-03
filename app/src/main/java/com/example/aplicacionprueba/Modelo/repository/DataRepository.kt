package com.example.aplicacionprueba.Modelo.repository

import com.example.aplicacionprueba.Modelo.InventoryItem
import com.example.aplicacionprueba.Modelo.Noticias
import com.example.aplicacionprueba.Modelo.network.PrestamosApi
import com.example.aplicacionprueba.Modelo.network.RetrofitClient
class DataRepository (
    private val api: PrestamosApi = RetrofitClient.instance
) {
    /**
     * Obtiene la lista del catálogo de equipos.
     */
    suspend fun getCatalog(): Result<List<InventoryItem>> {
        return try {
            val response = api.getCatalog()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error al cargar el catálogo. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtiene la lista de noticias.
     */
    suspend fun getNews(): Result<List<Noticias>> {
        return try {
            val response = api.getNews()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Error al cargar las noticias. Código: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}