package com.fabian.mobile.shophouseapp.main.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.R
import com.fabian.mobile.shophouseapp.main.ui.screens.search.SearchBar
import com.fabian.mobile.shophouseapp.ui.theme.Orange
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplatePrincipal(
    title: String,
    userName: String,
    mostrarBotonAtras: Boolean,
    mostrarCajaBusqueda: Boolean,
    mostrarCarroCompras: Boolean,
    mostrarRegistrarUsuario: Boolean,
    isLogin: Boolean,
    color:Color = Color.Black,
    atras: () -> Unit,
    iniciarSesion: () -> Unit,
    cerrarSesion: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(topBar = {
        var openMenu by rememberSaveable { mutableStateOf(false) }
        TopAppBar(
            title = {
                if (mostrarCajaBusqueda) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        searchText = "",
                        isEmpty = false,
                        onValueChange = {},
                        onEmptyOnClick = { /*TODO*/ }) {

                    }
                } else {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = stringResource(id = R.string.app_name),
                        fontSize = 30.sp
                    )
                }
            },
            actions = {
                if (mostrarCarroCompras) {
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(percent = 50))
                            .size(40.dp)
                            .background(Color.White), onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                }
                if (mostrarRegistrarUsuario) {
                    IconButton(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(percent = 50))
                            .size(40.dp)
                            .background(Color.White), onClick = { openMenu = !openMenu }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier
                            .width(200.dp)
                            .background(Color.White),
                        expanded = openMenu,
                        onDismissRequest = { openMenu = false }) {

                        if (isLogin) {
                            DropdownMenuItem(onClick = {
                                openMenu = false
                            }, text = {
                                Text(
                                    text = userName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            })
                            Divider()
                            DropdownMenuItem(onClick = {
                                openMenu = false
                                cerrarSesion()
                            }, text = {
                                Text(text = "Cerrar Sesión", fontSize = 20.sp)
                            })
                        } else {
                            DropdownMenuItem(onClick = {
                                openMenu = false
                                iniciarSesion()
                            }, text = {
                                Text(text = "Iniciar Sesión", fontSize = 20.sp)
                            })
                        }
                    }
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Orange
            )
        )
    }) {
        Column(modifier = Modifier.padding(it).background(if(color == Color.Black){
            MaterialTheme.colorScheme.background
        }else{
            Color.Black
        })) {
            Row(
                modifier = Modifier.padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 10.dp),
                    text = title,
                    fontSize = 24.sp,
                    color = color,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                if (mostrarBotonAtras) {
                    IconButton(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = color,
                                shape = RoundedCornerShape(percent = 50)
                            )
                            .size(30.dp), onClick = atras
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = null,
                            tint = color
                        )
                    }
                }
            }
            Box() {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previsualizacion() {
    ShopHouseAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TemplatePrincipal(
                title = "",
                userName = "wilson",
                mostrarBotonAtras = false,
                mostrarCajaBusqueda = true,
                mostrarCarroCompras = true,
                mostrarRegistrarUsuario = true,
                isLogin = false,
                iniciarSesion = {},
                cerrarSesion = {},
                atras = {}
            ) {

            }
        }
    }
}