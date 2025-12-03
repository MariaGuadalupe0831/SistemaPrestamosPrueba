package com.example.aplicacionprueba.Modelo

import com.google.gson.annotations.SerializedName

data class Administrador(
    @SerializedName("id_admin") val idAdmin: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido_paterno") val apellidoPaterno: String,
    @SerializedName("apellido_materno") val apellidoMaterno: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("rol") val rol: String = "admin"
    // 'contrasena' NO debe transferirse.
)
