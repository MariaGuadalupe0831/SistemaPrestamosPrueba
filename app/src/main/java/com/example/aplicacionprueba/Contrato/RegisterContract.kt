package com.example.aplicacionprueba.Contrato

interface RegisterContract {
    // VIEW: Interfaz que implementa la Activity/Fragment
    interface View {
        fun showLoading()
        fun hideLoading()
        fun navigateToLogin()
        fun showRegisterError(message: String)
        fun showRegisterSuccess()

        // Métodos para obtener datos del formulario (basados en activity_registro.xml)
        fun getNombre(): String
        fun getApellidoPaterno(): String
        fun getApellidoMaterno(): String
        fun getMatricula(): String
        fun getCorreo(): String
        fun getContrasena(): String
    }

    // PRESENTER: Interfaz que implementará la lógica de negocio
    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onRegisterButtonClicked()
    }
}