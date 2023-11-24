package com.fabian.mobile.shophouseapp.navegacion

sealed class TiendaAppScreens(val route: String) {

    object CategoriaScreen : TiendaAppScreens(route = "CategoriaScreen")
    object ProductoScreen : TiendaAppScreens("ProductoScreen/{catId}/{nombre}") {
        fun createRoute(catId: String? = "", nombre: String? = "") = "ProductoScreen/$catId/$nombre"
    }
    object IniciarSesionScreen : TiendaAppScreens(route = "IniciarSesionScreen")

    object RegistrarseScreen:TiendaAppScreens(route = "RegistrarseScreen")
}