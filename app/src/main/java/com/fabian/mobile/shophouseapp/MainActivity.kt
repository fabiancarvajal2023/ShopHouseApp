package com.fabian.mobile.shophouseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.fabian.mobile.shophouseapp.carro.ui.CarritoViewModel
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaViewModel
import com.fabian.mobile.shophouseapp.cliente.constants.ClienteConstants
import com.fabian.mobile.shophouseapp.cliente.ui.IniciarSesionViewModel
import com.fabian.mobile.shophouseapp.cliente.ui.RegistrarseViewModel
import com.fabian.mobile.shophouseapp.main.ui.screens.TemplatePrincipal
import com.fabian.mobile.shophouseapp.main.util.Preferences
import com.fabian.mobile.shophouseapp.navegacion.TiendaAppScreens
import com.fabian.mobile.shophouseapp.navegacion.TiendaNavigation
import com.fabian.mobile.shophouseapp.producto.ui.ProductoViewModel
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val categoriaViewModel: CategoriaViewModel by viewModels()
    private val productoViewModel: ProductoViewModel by viewModels()
    private val registrarseViewModel: RegistrarseViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private val iniciarSesionViewModel: IniciarSesionViewModel by viewModels()
    private val carritoViewModel: CarritoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopHouseAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val coroutineScope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    val atras: () -> Unit = {
                        navController.navigateUp()
                    }
                    val currentScreen by mainViewModel.currentScreen.collectAsState()
                    val title by mainViewModel.title.collectAsState()
                    val userName by mainViewModel.userName.collectAsState()
                    val mostrarBotonAtras by mainViewModel.mostrarBotonAtras.collectAsState()
                    val mostrarCajaBusqueda by mainViewModel.mostrarCajaBusqueda.collectAsState()
                    val mostrarCarroCompras by mainViewModel.mostrarCarroCompras.collectAsState()
                    val mostrarRegistrarUsuario by mainViewModel.mostrarRegistrarUsuario.collectAsState()
                    var color by rememberSaveable { mutableStateOf("Black") }
                    val setHeaderParams: (screen: ScreensEnum, title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit =
                        { screen, title, c, mostrarBotonAtras, mostrarCajaBusqueda, mostrarCarroCompras, mostrarRegistrarUsuario ->
                            mainViewModel.setTitle(title = title)
                            mainViewModel.setMostrarBotonAtras(mostrarBotonAtras)
                            mainViewModel.setMostrarCajaBusqueda(mostrarCajaBusqueda)
                            mainViewModel.setMostrarRegistrarUsuario(mostrarRegistrarUsuario)
                            mainViewModel.setMostrarCarroCompras(mostrarCarroCompras)
                            mainViewModel.changeScreen(screen)
                            color = c
                        }
                    val searchCategoria by categoriaViewModel.search.collectAsState()
                    val searchProducto by productoViewModel.search.collectAsState()
                    TemplatePrincipal(
                        title = title,
                        userName = userName,
                        searchText = when (currentScreen) {
                            ScreensEnum.Home -> {
                                searchCategoria
                            }

                            ScreensEnum.Productos -> {
                                searchProducto
                            }

                            else -> {
                                ""
                            }
                        },
                        mostrarBotonAtras = mostrarBotonAtras,
                        mostrarCajaBusqueda = mostrarCajaBusqueda,
                        mostrarCarroCompras = mostrarCarroCompras,
                        mostrarRegistrarUsuario = mostrarRegistrarUsuario,
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
                        onValueChange = {
                            when (currentScreen) {
                                ScreensEnum.Home -> {
                                    categoriaViewModel.changeSearch(it)
                                }

                                ScreensEnum.Productos -> {
                                    productoViewModel.changeSearch(it)
                                }

                                else -> {
                                }
                            }
                        },
                        iniciarSesion = {
                            navController.navigate(TiendaAppScreens.IniciarSesionScreen.route)
                        },
                        cerrarSesion = {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    val preference = Preferences(context = context)
                                    preference.saveString(ClienteConstants.NOMBRE, "")
                                    mainViewModel.changeUserName("")
                                }
                            }
                        },
                        carritoOnclick = {
                            navController.navigate(TiendaAppScreens.CarritoScreen.route)
                        },
                        atras = atras
                    ) {
                        TiendaNavigation(
                            navController = navController,
                            setHeaderParams = setHeaderParams,
                            categoriaViewModel = categoriaViewModel,
                            productoViewModel = productoViewModel,
                            registrarseViewModel = registrarseViewModel,
                            iniciarSesionViewModel = iniciarSesionViewModel,
                            carritoViewModel = carritoViewModel,
                            onClickCategoria = { catId, nombre ->
                                navController.navigate(
                                    TiendaAppScreens.ProductoScreen.createRoute(
                                        catId = catId.toString(),
                                        nombre = nombre
                                    )
                                )
                            },
                            onClickRegistrarse = {
                                navController.navigate(TiendaAppScreens.RegistrarseScreen.route)
                            },
                            onBack = atras,
                            loginSuccess = {
                                coroutineScope.launch {
                                    withContext(Dispatchers.IO) {
                                        val preference = Preferences(context = context)
                                        val nombre =
                                            preference.getString(ClienteConstants.NOMBRE)
                                        mainViewModel.changeUserName(nombre)
                                    }
                                }

                            },
                            addOnclick = {
                                carritoViewModel.addProducto(producto = it)
                            },
                            onLogin = {
                                navController.navigate(TiendaAppScreens.IniciarSesionScreen.route)
                            }
                        )
                    }

                    LaunchedEffect(key1 = Unit) {
                        val preference = Preferences(context = context)
                        val nombre = preference.getString(ClienteConstants.NOMBRE)
                        mainViewModel.changeUserName(nombre)
                    }
                }
            }
        }
    }
}