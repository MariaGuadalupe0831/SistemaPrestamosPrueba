package com.example.aplicacionprueba.Modelo
import com.google.gson.annotations.SerializedName
data class User(
    @SerializedName("id_usuario") val idUsuario: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido_paterno") val apellidoPaterno: String?,
    @SerializedName("apellido_materno") val apellidoMaterno: String?,
    @SerializedName("matricula") val matricula: String?, // Nullable porque admin no tiene matrícula
    @SerializedName("correo") val correo: String,
    @SerializedName("tel_fijo") val telFijo: String? = null,
    @SerializedName("tel_celular") val telCelular: String? = null,
    @SerializedName("rol") val rol: String = "usuario" // Para identificar el tipo de usuario
    // NOTA: 'contrasena', 'ip_registro', 'pregunta_seguridad', 'respuesta_seguridad' NO deben transferirse.
)
