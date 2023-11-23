package com.fabian.mobile.shophouseapp.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaScreen
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaViewModel
import com.fabian.mobile.shophouseapp.cliente.ui.IniciarSesionScreen
import com.fabian.mobile.shophouseapp.producto.ui.ProductoScreen
import com.fabian.mobile.shophouseapp.producto.ui.ProductoViewModel

@Composable
fun TiendaNavigation(
    navController: NavHostController,
    setHeaderParams: (title: String, color: String, mostrarBotonAtras:Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit,
    categoriaViewModel: CategoriaViewModel,
    productoViewModel: ProductoViewModel,
    onClickCategoria: (Int, String) -> Unit
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
                    categoriaNombre = nombre
                )
            } else {
                ProductoScreen(
                    productoViewModel = productoViewModel,
                    setHeaderParams = setHeaderParams,
                    categoriaId = null,
                    categoriaNombre = ""
                )
            }
        }
        composable(route = TiendaAppScreens.IniciarSesionScreen.route)
        {
            IniciarSesionScreen(setHeaderParams = setHeaderParams)
        }
    }
}