package com.fabian.mobile.shophouseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.fabian.mobile.MainViewModel
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaViewModel
import com.fabian.mobile.shophouseapp.main.ui.screens.TemplatePrincipal
import com.fabian.mobile.shophouseapp.navegacion.TiendaAppScreens
import com.fabian.mobile.shophouseapp.navegacion.TiendaNavigation
import com.fabian.mobile.shophouseapp.producto.ui.ProductoViewModel
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

class MainActivity : ComponentActivity() {

    private val categoriaViewModel: CategoriaViewModel by viewModels()
    private val productoViewModel: ProductoViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopHouseAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val atras: () -> Unit = {
                        navController.navigateUp()
                    }
                    val title by mainViewModel.title.collectAsState()
                    val userName by mainViewModel.userName.collectAsState()
                    val mostrarBotonAtras by mainViewModel.mostrarBotonAtras.collectAsState()
                    val mostrarCajaBusqueda by mainViewModel.mostrarCajaBusqueda.collectAsState()
                    val mostrarCarroCompras by mainViewModel.mostrarCarroCompras.collectAsState()
                    val mostrarRegistrarUsuario by mainViewModel.mostrarRegistrarUsuario.collectAsState()
                    val isLogin by mainViewModel.isLogin.collectAsState()
                    var color by rememberSaveable { mutableStateOf("Black") }
                    val setHeaderParams: (title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit =
                        { title, c, mostrarBotonAtras, mostrarCajaBusqueda, mostrarCarroCompras, mostrarRegistrarUsuario ->
                            mainViewModel.setTitle(title = title)
                            mainViewModel.setMostrarBotonAtras(mostrarBotonAtras)
                            mainViewModel.setMostrarCajaBusqueda(mostrarCajaBusqueda)
                            mainViewModel.setMostrarRegistrarUsuario(mostrarRegistrarUsuario)
                            mainViewModel.setMostrarCarroCompras(mostrarCarroCompras)
                            mainViewModel.setIsLogin(isLogin)
                            color = c
                        }
                    TemplatePrincipal(
                        title = title,
                        userName = userName,
                        mostrarBotonAtras = mostrarBotonAtras,
                        mostrarCajaBusqueda = mostrarCajaBusqueda,
                        mostrarCarroCompras = mostrarCarroCompras,
                        mostrarRegistrarUsuario = mostrarRegistrarUsuario,
                        isLogin = isLogin,
                        color = when (color) {
                            "Black" -> {
                                Color.Black
                            }
                            "White" -> {
                                Color.White
                            }
                            else -> {
                                Color.Black
                            }
                        },
                        iniciarSesion = {
                            navController.navigate(TiendaAppScreens.IniciarSesionScreen.route)
                        },
                        cerrarSesion = {

                        },
                        atras = atras
                    ) {

                        TiendaNavigation(
                            navController = navController,
                            setHeaderParams = setHeaderParams,
                            categoriaViewModel = categoriaViewModel,
                            productoViewModel = productoViewModel,
                            onClickCategoria = { catId, nombre ->
                                navController.navigate(
                                    TiendaAppScreens.ProductoScreen.createRoute(
                                        catId = catId.toString(),
                                        nombre = nombre
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}