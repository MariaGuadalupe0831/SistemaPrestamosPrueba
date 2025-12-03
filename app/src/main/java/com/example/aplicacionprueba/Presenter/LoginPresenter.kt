package com.example.aplicacionprueba.Presenter
import android.util.Log
import com.example.aplicacionprueba.Modelo.repository.LoginRepository
import com.example.aplicacionprueba.Contrato.LoginContract
import kotlinx.coroutines.*

class LoginPresenter(private val repository: LoginRepository, private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : LoginContract.Presenter {
    private var view: LoginContract.View? = null

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        scope.cancel() // Cancelamos las coroutines cuando la Vista se destruye
        this.view = null
    }

    override fun onLoginButtonClicked() {
        val correo = view?.getEmailInput() ?: "" // Asegura que no sea nulo, devuelve "" si falla
        val contrasena = view?.getPasswordInput() ?: ""

        // ***** AÑADE ESTA LÍNEA TEMPORALMENTE *****
        android.util.Log.d("LOGIN_DEBUG", "Correo: '$correo', Contraseña recibida, Longitud: ${contrasena.length}")
        // *****************************************

        if (correo.isEmpty() || contrasena.isEmpty()) {
            view?.showLoginError("Por favor, ingresa correo y contraseña.")
            return // Si recibes el error, el Presenter está viendo cadenas vacías.
        }

        view?.showLoading()

        // Ejecutar la llamada al Repository en un Coroutine
        scope.launch(Dispatchers.IO) {
            val result = repository.attemptLogin(correo, contrasena)

            withContext(Dispatchers.Main) {
                view?.hideLoading()

                result.fold(
                    onSuccess = { response ->
                        // Guardar datos del usuario (SharedPreferences o DataStore)
                        val rol = response.userData?.rol

                        if (rol == "admin") {
                            view?.navigateToAdminHome()
                        } else {
                            view?.navigateToUserHome()
                        }
                    },
                    onFailure = { error ->
                        view?.showLoginError(error.message ?: "Error de autenticación desconocido.")
                    }
                )
            }
        }
    }
}