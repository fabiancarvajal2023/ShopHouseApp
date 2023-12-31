package com.fabian.mobile.shophouseapp.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fabian.mobile.shophouseapp.ScreensEnum
import com.fabian.mobile.shophouseapp.carro.ui.CarritoScreen
import com.fabian.mobile.shophouseapp.carro.ui.CarritoViewModel
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaScreen
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaViewModel
import com.fabian.mobile.shophouseapp.cliente.ui.IniciarSesionScreen
import com.fabian.mobile.shophouseapp.cliente.ui.IniciarSesionViewModel
import com.fabian.mobile.shophouseapp.cliente.ui.RegistrarseScreen
import com.fabian.mobile.shophouseapp.cliente.ui.RegistrarseViewModel
import com.fabian.mobile.shophouseapp.producto.Producto
import com.fabian.mobile.shophouseapp.producto.ui.ProductoScreen
import com.fabian.mobile.shophouseapp.producto.ui.ProductoViewModel

@Composable
fun TiendaNavigation(
    navController: NavHostController,
    setHeaderParams: (screen: ScreensEnum, title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit,
    categoriaViewModel: CategoriaViewModel,
    productoViewModel: ProductoViewModel,
    registrarseViewModel: RegistrarseViewModel,
    iniciarSesionViewModel: IniciarSesionViewModel,
    carritoViewModel: CarritoViewModel,
    onClickCategoria: (Int, String) -> Unit,
    onClickRegistrarse: () -> Unit,
    onBack: () -> Unit,
    loginSuccess: () -> Unit,
    addOnclick: (Producto) -> Unit,
    onLogin:()->Unit
) {


    NavHost(
        navController = navController,
        startDestination = TiendaAppScreens.CategoriaScreen.route
    ) {

        composable(route = TiendaAppScreens.CategoriaScreen.route)
        {
            CategoriaScreen(
                categoriaViewModel = categoriaViewModel,
                setHeaderParams = setHeaderParams,
                onClickCategoria = onClickCategoria
            )
        }

        composable(
            route = TiendaAppScreens.ProductoScreen.route,
            arguments = listOf(navArgument("catId") {}, navArgument("nombre") {})
        ) { backStackEntry ->
            val catIdString = backStackEntry.arguments?.getString("catId") ?: ""
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            if (catIdString.isNotEmpty() && nombre.isNotEmpty()) {
                ProductoScreen(
                    productoViewModel = productoViewModel,
                    setHeaderParams = setHeaderParams,
                    categoriaId = catIdString.toInt(),
                    categoriaNombre = nombre,
                    addOnclick = addOnclick
                )
            } else {
                ProductoScreen(
                    productoViewModel = productoViewModel,
                    setHeaderParams = setHeaderParams,
                    categoriaId = null,
                    categoriaNombre = "",
                    addOnclick = addOnclick
                )
            }
        }
        composable(route = TiendaAppScreens.IniciarSesionScreen.route)
        {
            IniciarSesionScreen(
                iniciarSesionViewModel = iniciarSesionViewModel,
                setHeaderParams = setHeaderParams,
                onClickRegistrarse = onClickRegistrarse,
                onback = onBack,
                loginSuccess = loginSuccess
            )
        }

        composable(route = TiendaAppScreens.RegistrarseScreen.route)
        {
            RegistrarseScreen(
                registrarseViewModel = registrarseViewModel,
                setHeaderParams = setHeaderParams
            )
        }

        composable(route = TiendaAppScreens.CarritoScreen.route)
        {
            CarritoScreen(
                carritoViewModel = carritoViewModel,
                setHeaderParams = setHeaderParams,
                onBack = onBack,
                onLogin = onLogin
            )
        }
    }
}