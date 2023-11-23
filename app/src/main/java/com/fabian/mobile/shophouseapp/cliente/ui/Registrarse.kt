package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.mobile.shophouseapp.main.ui.screens.loading.SimpleLoading
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun Registrarse(
    registrarseViewModel: RegistrarseViewModel,
    setHeaderParams: (title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit
) {
    val loading by registrarseViewModel.loading.collectAsState()

    Screen(loading = loading)
    LaunchedEffect(key1 = Unit) {
        setHeaderParams("Registrarse", "White", true, false, false, false)
        registrarseViewModel.cargarTiposidentificacion()
    }
}

@Composable
private fun Screen(
    loading: Boolean,
    /*email: String,
    password: String,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    iniciarSesion: () -> Unit,
    registrarse: () -> Unit*/

) {
    if (loading) {
        SimpleLoading(modifier = Modifier.fillMaxSize())
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp)
        ) {



            /*var showPassword by rememberSaveable { mutableStateOf(false) }
        CustomBasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 50)),
            value = email,
            onValueChange = onChangeEmail,
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Email,
                    contentDescription = ""
                )
            }, placeholder = {
                Text(
                    text = "Correo electrónico",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            })
        Spacer(modifier = Modifier.height(20.dp))
        CustomBasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 50)),
            value = password,
            onValueChange = onChangePassword,
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Key,
                    contentDescription = ""
                )
            }, trailingIcon = {
                val image = if (showPassword) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = image, contentDescription = "show password")
                }
            }, placeholder = {
                Text(
                    text = "Contraseña",
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            }, visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonGeneral(
            modifier = Modifier.fillMaxWidth(),
            value = "Iniciar sesion",
            onCLick = iniciarSesion
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "No tienes una cuenta? ", color = Color.White, fontSize = 18.sp)
                Text(
                    modifier = Modifier.clickable {
                        registrarse()
                    },
                    text = "regitrate",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }*/
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ShopHouseAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Screen(
                loading = false
            )
        }
    }
}