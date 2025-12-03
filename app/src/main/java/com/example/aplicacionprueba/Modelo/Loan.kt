package com.example.aplicacionprueba.Modelo
import com.google.gson.annotations.SerializedName
data class Loan(
    @SerializedName("id_prestamo") val idPrestamo: Int,
    @SerializedName("id_admin") val idAdmin: Int, // El administrador que autorizó/gestionó el préstamo
    @SerializedName("id_usuario") val idUsuario: Int,
    @SerializedName("id_equipo") val idEquipo: Int,
    @SerializedName("fecha_prestamo") val fechaPrestamo: String, // Usar String o Date
    @SerializedName("fecha_devolucion") val fechaDevolucion: String, // Usar String o Date
    @SerializedName("estado_prestamo") val estadoPrestamo: String, // Ej: "DEVUELTO", "EN PROCESO"
    @SerializedName("observaciones") val observaciones: String?
)
