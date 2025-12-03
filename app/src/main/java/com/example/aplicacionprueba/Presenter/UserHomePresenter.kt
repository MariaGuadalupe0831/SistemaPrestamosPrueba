package com.example.aplicacionprueba.Presenter
import com.example.aplicacionprueba.Contrato.UserHomeContract
import com.example.aplicacionprueba.Modelo.User
import com.example.aplicacionprueba.Modelo.repository.DataRepository // Ya implementamos este Repository
import kotlinx.coroutines.*

class UserHomePresenter(
    // Dependencia del repositorio para obtener los datos.
    private val dataRepository: DataRepository,
    // Scope para manejar coroutines (se cancela al destruir la Vista).
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : UserHomeContract.Presenter {

    private var view: UserHomeContract.View? = null
    private var currentUser: User? = null // Para guardar los datos del usuario logueado

    override fun attachView(view: UserHomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        scope.cancel() // Es importante cancelar el scope al destruir la Activity
        this.view = null
    }

    override fun loadInitialData(user: User) {
        currentUser = user

        // 1. Mostrar mensaje de bienvenida usando el nombre del usuario
        view?.showWelcomeMessage(user.nombre)

        // 2. Iniciar carga de noticias
        loadNews()
    }
    private fun loadNews() {
        view?.showLoading()

        // Lanzar coroutine en el Dispatcher.IO para operaciones de red
        scope.launch(Dispatchers.IO) {

            // 1. Llamada al Repositorio para obtener los datos
            val result = dataRepository.getNews()

            // 2. Volver al hilo principal (Main Dispatcher) para actualizar la UI
            withContext(Dispatchers.Main) {
                view?.hideLoading()

                // 3. Manejar el resultado de la llamada (éxito o fallo)
                result.fold(
                    onSuccess = { newsList ->
                        // Si la carga fue exitosa, mostrar la lista de noticias
                        view?.displayNews(newsList)
                    },
                    onFailure = { error ->
                        // Si hubo un error (red, servidor), mostrar un mensaje
                        view?.displayError("Error al cargar noticias: ${error.message ?: "Desconocido"}")
                    }
                )
            }
        }
    }

    override fun onLogoutClicked() {
        scope.cancel()
        view?.navigateToLogin()
    }
}