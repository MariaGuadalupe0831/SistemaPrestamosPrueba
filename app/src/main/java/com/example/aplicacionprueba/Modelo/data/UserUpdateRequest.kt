package com.example.aplicacionprueba.Modelo.data

import com.google.gson.annotations.SerializedName

data class UserUpdateRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("correo") val correo: String?,
    @SerializedName("contrasena") val contrasena: String?
)