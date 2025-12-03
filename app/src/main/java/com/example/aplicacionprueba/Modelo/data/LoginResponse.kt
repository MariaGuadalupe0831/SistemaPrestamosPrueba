package com.example.aplicacionprueba.Modelo.data

import com.example.aplicacionprueba.Modelo.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val userData: User? // Contiene el objeto User si el login es exitoso
)