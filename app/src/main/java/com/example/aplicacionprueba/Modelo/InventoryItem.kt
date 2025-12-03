package com.example.aplicacionprueba.Modelo
import com.google.gson.annotations.SerializedName

data class InventoryItem(
    @SerializedName("id_equipo") val idEquipo: Int,
    @SerializedName("nombre_equipo") val nombreEquipo: String,
    @SerializedName("modelo") val modeloId: Int, // ID de la tabla 'modelo' (se podría obtener el nombre después)
    @SerializedName("numero_inventario") val numeroInventario: String?,
    @SerializedName("stock") val stock: Int,
    @SerializedName("disponibilidad") val disponibilidad: Int,
    @SerializedName("id_tipo") val idTipo: Int, // ID de la tabla 'tipo_equipo'
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("imagen_blob") val imagenBlob: String?, // Probablemente una URL si no es BLOB directo
    @SerializedName("imagen_mime") val imagenMime: String?
)
