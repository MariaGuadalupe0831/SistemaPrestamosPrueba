package com.example.aplicacionprueba.Contrato
import com.example.aplicacionprueba.Modelo.Noticias
import com.example.aplicacionprueba.Modelo.User
interface UserHomeContract {
    interface View {
        fun showWelcomeMessage(userName: String)
        fun displayNews(newsList: List<Noticias>)
        fun showLoading()
        fun hideLoading()
        fun displayError(message: String)
        fun navigateToLogin()
        // Puedes añadir aquí métodos para ver el catálogo o el historial
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun loadInitialData(user: User) // Carga noticias, contacto, etc.
        fun onLogoutClicked()
    }
}