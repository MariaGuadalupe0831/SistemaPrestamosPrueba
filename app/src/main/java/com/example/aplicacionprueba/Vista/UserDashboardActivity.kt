package com.example.aplicacionprueba.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionprueba.R
import com.example.aplicacionprueba.Contrato.UserHomeContract
import com.example.aplicacionprueba.Modelo.Noticias
import com.example.aplicacionprueba.Vista.user.NewsAdapter
import com.example.aplicacionprueba.Modelo.User
import com.example.aplicacionprueba.Modelo.repository.DataRepository // Dependencia
import com.example.aplicacionprueba.Presenter.UserHomePresenter

class UserDashboardActivity : AppCompatActivity(), UserHomeContract.View {
    private lateinit var presenter: UserHomeContract.Presenter
    private lateinit var tvWelcome: TextView
    private lateinit var rvNews: RecyclerView
    private lateinit var progressBarHome: ProgressBar
    private lateinit var btnLogout: Button

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. Inicialización de Vistas
        tvWelcome = findViewById(R.id.tvWelcomeMessage)
        rvNews = findViewById(R.id.rvNews)
        progressBarHome = findViewById(R.id.progressBarHome)
        btnLogout = findViewById(R.id.btnLogout)

        // 2. Configuración del RecyclerView
        newsAdapter = NewsAdapter(emptyList()) // Debes crear esta clase (Adapter)
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = newsAdapter

        // 3. Inicialización del Presenter
        val user: User? = intent.getParcelableExtra("USER_DATA") // Obtener datos del Login
        val repository = DataRepository()
        presenter = UserHomePresenter(repository)
        presenter.attachView(this)

        if (user != null) {
            presenter.loadInitialData(user)
        } else {
            displayError("Error: No se encontró la información del usuario.")
            navigateToLogin()
        }

        // 4. Listener del Cierre de Sesión
        btnLogout.setOnClickListener {
            presenter.onLogoutClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showWelcomeMessage(userName: String) {
        tvWelcome.text = userName
    }

    override fun displayNews(newsList: List<Noticias>) {
        if (newsList.isEmpty()) {
            displayError("No hay noticias disponibles.")
        }
        newsAdapter.updateData(newsList)
    }

    override fun showLoading() {
        progressBarHome.visibility = android.view.View.VISIBLE
        rvNews.visibility = android.view.View.GONE
    }

    override fun hideLoading() {
        progressBarHome.visibility = android.view.View.GONE
        rvNews.visibility = android.view.View.VISIBLE
    }

    override fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToLogin() {
        val intent = Intent(this, login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}