package com.example.aplicacionprueba.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacionprueba.R
import com.example.aplicacionprueba.Modelo.repository.LoginRepository
import com.example.aplicacionprueba.Presenter.LoginPresenter
import com.example.aplicacionprueba.Contrato.LoginContract

class login : AppCompatActivity(), LoginContract.View {

    // 1. Declarar las vistas manualmente
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRegistrar: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var presenter: LoginContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Asignar vistas usando findViewById() y TUS IDs
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword) // Usando el ID de tu XML
        btnLogin = findViewById(R.id.btnLogin)
        tvRegistrar = findViewById(R.id.tvRegistrar) // Usando el ID de tu XML

        // Asumiendo que añades un ProgressBar con este ID:
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = android.view.View.GONE // Ocultar al inicio

        // Inicializar el Presenter y el Modelo
        val repository = LoginRepository()
        presenter = LoginPresenter(repository)
        presenter.attachView(this)

        // 2. Eventos de Clic
        btnLogin.setOnClickListener {
            presenter.onLoginButtonClicked()
        }

        tvRegistrar.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoading() {
        progressBar.visibility = android.view.View.VISIBLE
        btnLogin.isEnabled = false
        tvRegistrar.isEnabled = false
    }

    override fun hideLoading() {
        progressBar.visibility = android.view.View.GONE
        btnLogin.isEnabled = true
        tvRegistrar.isEnabled = true
    }

    override fun navigateToUserHome() {
        // Navega a la pantalla principal del Usuario
        val intent = Intent(this, UserDashboardActivity::class.java)

        // Flags para limpiar la pila de actividades y evitar que el usuario regrese al Login
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Finaliza esta actividad
    }

    override fun navigateToAdminHome() {
        // Navega a la pantalla principal del Administrador
        val intent = Intent(this, AdminDashboardActivity::class.java)

        // Flags para limpiar la pila de actividades y evitar que el usuario regrese al Login
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Finaliza esta actividad
    }

    override fun showLoginError(message: String) {
        // Muestra un mensaje de error al usuario (ej: usando un Toast)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        // Aseguramos que la UI regrese a su estado normal si el error ocurre DESPUÉS del loading
        hideLoading()
    }

    override fun getEmailInput(): String {
        return etCorreo.text.toString() // Usa la variable asignada
    }

    override fun getPasswordInput(): String {
        return etPassword.text.toString() // Usa la variable asignada
    }
}