package com.example.aplicacionprueba.Modelo.network

import com.example.aplicacionprueba.Modelo.GeneralResponse
import com.example.aplicacionprueba.Modelo.InventoryItem
import com.example.aplicacionprueba.Modelo.Loan
import com.example.aplicacionprueba.Modelo.Noticias
import com.example.aplicacionprueba.Modelo.User
import com.example.aplicacionprueba.Modelo.data.LoanRequest
import com.example.aplicacionprueba.Modelo.data.LoginRequest
import com.example.aplicacionprueba.Modelo.data.LoginResponse
import com.example.aplicacionprueba.Modelo.data.RegisterRequest
import com.example.aplicacionprueba.Modelo.data.UserIdRequest
import com.example.aplicacionprueba.Modelo.data.UserUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PrestamosApi {
    // ===================================================
    // 1. AUTENTICACIÓN Y REGISTRO
    // ===================================================

    // POST: https://prestamosequipos.grupoahost.com/api/login.php
    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // POST: https://prestamosequipos.grupoahost.com/api/register.php
    @POST("register.php")
    suspend fun register(@Body request: RegisterRequest): Response<GeneralResponse>

    // POST: https://prestamosequipos.grupoahost.com/api/perfil/update.php
    @POST("perfil/update.php")
    suspend fun updateProfile(@Body request: UserUpdateRequest): Response<GeneralResponse>

    // ===================================================
    // 2. USUARIO: CATÁLOGO Y NOTICIAS
    // ===================================================

    // GET: https://prestamosequipos.grupoahost.com/api/catalogo/list.php
    @GET("catalogo/lista.php")
    suspend fun getCatalog(): Response<List<InventoryItem>>

    // GET: https://prestamosequipos.grupoahost.com/api/noticias/list.php
    @GET("noticias/list.php")
    suspend fun getNews(): Response<List<Noticias>>

    // POST: https://prestamosequipos.grupoahost.com/api/prestamos/solicitar.php
    @POST("prestamos/solicitar.php")
    suspend fun requestLoan(@Body request: LoanRequest): Response<GeneralResponse>

    // GET: https://prestamosequipos.grupoahost.com/api/prestamos/historial.php?user_id=X
    @GET("prestamos/historial.php")
    suspend fun getUserLoanHistory(@Query("user_id") userId: Int): Response<List<Loan>>

    // ===================================================
    // 3. ADMINISTRADOR (EJEMPLOS)
    // ===================================================

    // GET: https://prestamosequipos.grupoahost.com/api/admin/usuarios/list.php
    @GET("admin/usuarios/list.php")
    suspend fun getAllUsers(): Response<List<User>>

    // POST: https://prestamosequipos.grupoahost.com/api/admin/usuarios/delete.php
    @POST("admin/usuarios/delete.php")
    suspend fun deleteUser(@Body request: UserIdRequest): Response<GeneralResponse>

    // GET: https://prestamosequipos.grupoahost.com/api/admin/prestamos/historial_usuario.php?user_id=X
    @GET("admin/prestamos/historial_usuario.php")
    suspend fun getAdminLoanHistory(@Query("user_id") userId: Int): Response<List<Loan>>

}