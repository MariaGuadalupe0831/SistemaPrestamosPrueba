package com.example.aplicacionprueba.Modelo
import com.google.gson.annotations.SerializedName
data class Noticias(
    @SerializedName("id_noticia") val idNoticia: Int,
    @SerializedName("titulo") val titulo: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("contenido") val contenido: String,
    @SerializedName("imagen_blob") val imagenBlob: String?, // URL de la imagen
    @SerializedName("imagen_mime") val imagenMime: String?,
    @SerializedName("fecha_publicacion") val fechaPublicacion: String, // Usar String o Date
    @SerializedName("id_admin") val idAdmin: Int,
    @SerializedName("activa") val activa: Int // 1=Activa, 0=Inactiva
)
