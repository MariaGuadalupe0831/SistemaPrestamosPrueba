package com.example.aplicacionprueba.Contrato

interface LoginContract {
    // VIEW: Interfaz que implementa la Activity/Fragment
    interface View {
        fun showLoading()
        fun hideLoading()
        fun navigateToUserHome()
        fun navigateToAdminHome()
        fun showLoginError(message: String)
        fun getEmailInput(): String
        fun getPasswordInput(): String
    }

    // PRESENTER: Interfaz que implementa la lógica de negocio
    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onLoginButtonClicked()
    }
}