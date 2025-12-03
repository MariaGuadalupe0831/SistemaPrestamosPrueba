package com.example.aplicacionprueba.Modelo.data

import com.google.gson.annotations.SerializedName

data class UserIdRequest(
    @SerializedName("user_id") val userId: Int
)