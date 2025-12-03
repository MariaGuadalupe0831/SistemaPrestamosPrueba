package com.example.aplicacionprueba.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplicacionprueba.R
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.aplicacionprueba.Modelo.repository.RegisterRepository
import com.example.aplicacionprueba.Contrato.RegisterContract
import com.example.aplicacionprueba.Presenter.RegisterPresenter

class Registro : AppCompatActivity(), RegisterContract.View {

    // Vistas - Usando findViewByID y los IDs de activity_registro.xml
    private lateinit var etNombre: EditText
    private lateinit var etApellidoPaterno: EditText
    private lateinit var etApellidoMaterno: EditText
    private lateinit var etMatricula: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var progressBarRegistro: ProgressBar

    private lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Asignar vistas (FindViewById)
        etNombre = findViewById(R.id.et_nombre)
        etApellidoPaterno = findViewById(R.id.et_apellido_paterno)
        etApellidoMaterno = findViewById(R.id.et_apellido_materno)
        etMatricula = findViewById(R.id.et_matricula)
        etCorreo = findViewById(R.id.et_correo_registro)
        etContrasena = findViewById(R.id.et_password_registro)
        btnRegistrar = findViewById(R.id.btn_registrar)
        progressBarRegistro = findViewById(R.id.progressBarRegistro)
        progressBarRegistro.visibility = android.view.View.GONE

        // 2. Inicializar Presenter
        val repository = RegisterRepository()
        presenter = RegisterPresenter(repository)
        presenter.attachView(this)

        // 3. Listener del botón de registro
        btnRegistrar.setOnClickListener {
            presenter.onRegisterButtonClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
    // IMPLEMENTACIÓN DE LOS MÉTODOS DE LA VISTA (RegisterContract.View)
    override fun showLoading() {
        progressBarRegistro.visibility = android.view.View.VISIBLE
        btnRegistrar.isEnabled = false
    }

    override fun hideLoading() {
        progressBarRegistro.visibility = android.view.View.GONE
        btnRegistrar.isEnabled = true
    }

    override fun navigateToLogin() {
        Toast.makeText(this, "¡Registro exitoso! Ya puedes iniciar sesión.", Toast.LENGTH_LONG).show()
    }

    override fun showRegisterError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showRegisterSuccess() {
        finish()
    }

    override fun getNombre(): String {
        return etNombre.text.toString()
    }

    override fun getApellidoPaterno(): String {
        return etApellidoPaterno.text.toString()
    }

    override fun getApellidoMaterno(): String {
        return etApellidoMaterno.text.toString()
    }

    override fun getMatricula(): String {
        return etMatricula.text.toString()
    }

    override fun getCorreo(): String {
        return etCorreo.text.toString()
    }

    override fun getContrasena(): String {
        return etContrasena.text.toString()
    }
}