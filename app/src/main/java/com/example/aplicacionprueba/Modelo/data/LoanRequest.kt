package com.example.aplicacionprueba.Modelo.data

import com.google.gson.annotations.SerializedName

data class LoanRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("fecha_solicitud") val fechaSolicitud: String // Formato YYYY-MM-DD
)