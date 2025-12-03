package com.example.aplicacionprueba.Modelo.data

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido_paterno") val apellidoPaterno: String,
    @SerializedName("apellido_materno") val apellidoMaterno: String?, // Puede ser opcional
    @SerializedName("matricula") val matricula: String?,
    @SerializedName("correo") val correo: String,
    @SerializedName("contrasena") val contrasena: String
)