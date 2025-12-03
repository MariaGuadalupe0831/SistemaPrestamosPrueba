package com.example.aplicacionprueba.Presenter
import com.example.aplicacionprueba.Modelo.data.RegisterRequest
import com.example.aplicacionprueba.Modelo.repository.RegisterRepository
import com.example.aplicacionprueba.Contrato.RegisterContract
import kotlinx.coroutines.*

class RegisterPresenter(private val repository: RegisterRepository, private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)) : RegisterContract.Presenter {

    private var view: RegisterContract.View? = null

    override fun attachView(view: RegisterContract.View) {
        this.view = view
    }

    override fun detachView() {
        scope.cancel() // Cancelar coroutines
        this.view = null
    }

    override fun onRegisterButtonClicked() {
        val nombre = view?.getNombre() ?: return
        val apPaterno = view?.getApellidoPaterno() ?: return
        val apMaterno = view?.getApellidoMaterno() ?: return
        val matricula = view?.getMatricula() ?: return
        val correo = view?.getCorreo() ?: return
        val contrasena = view?.getContrasena() ?: return

        // 1. Validación de datos (Lógica de Negocio)
        if (nombre.isEmpty() || apPaterno.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            view?.showRegisterError("Nombre, Apellido Paterno, Correo y Contraseña son obligatorios.")
            return
        }

        // Puedes añadir aquí validación de formato de email (Regex)

        view?.showLoading()

        // 2. Creación del objeto de solicitud
        val request = RegisterRequest(
            nombre = nombre,
            apellidoPaterno = apPaterno,
            apellidoMaterno = apMaterno,
            matricula = matricula,
            correo = correo,
            contrasena = contrasena
        )

        // 3. Llamada asíncrona al Repository (Modelo)
        scope.launch(Dispatchers.IO) {
            val result = repository.attemptRegister(request)

            withContext(Dispatchers.Main) {
                view?.hideLoading()

                result.fold(
                    onSuccess = { response ->
                        view?.showRegisterSuccess()
                        view?.navigateToLogin()
                    },
                    onFailure = { error ->
                        // Error de red, JSON o error devuelto por el servidor (ej. "Correo ya existe")
                        view?.showRegisterError(error.message ?: "Error desconocido al registrar.")
                    }
                )
            }
        }
    }

}