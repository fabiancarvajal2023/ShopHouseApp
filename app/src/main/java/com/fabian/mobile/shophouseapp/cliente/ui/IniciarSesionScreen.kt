package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.main.ui.screens.button.ButtonGeneral
import com.fabian.mobile.shophouseapp.main.ui.screens.textfield.CustomBasicTextField
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun IniciarSesionScreen(
    setHeaderParams: (title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit,
) {

    Screen(
        email = "",
        password = "",
        onChangeEmail = {},
        onChangePassword = {},
        iniciarSesion = {},
        registrarse = {})
    LaunchedEffect(key1 = Unit) {
        setHeaderParams("Iniciar sesión", "White", true, false, false, false)
    }
}

@Composable
private fun Screen(
    email: String,
    password: String,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    iniciarSesion: () -> Unit,
    registrarse: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp)
    ) {
        var showPassword by rememberSaveable { mutableStateOf(false) }
        Image(
            modifier = Modifier
                .align(CenterHorizontally)
                .size(100.dp),
            imageVector = Icons.Filled.Person,
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Color.White)
        )
        Spacer(modifier = Modifier.height(30.dp))
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
                    .align(BottomCenter)
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
                email = "",
                password = "",
                onChangeEmail = {},
                onChangePassword = {},
                iniciarSesion = {},
                registrarse = {})
        }
    }
}