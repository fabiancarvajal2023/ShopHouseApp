package com.fabian.mobile.shophouseapp.cliente.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.ScreensEnum
import com.fabian.mobile.shophouseapp.cliente.Tipoidentificacion
import com.fabian.mobile.shophouseapp.main.ui.screens.button.ButtonGeneral
import com.fabian.mobile.shophouseapp.main.ui.screens.dialogs.OneButtonDialog
import com.fabian.mobile.shophouseapp.main.ui.screens.loading.SimpleLoading
import com.fabian.mobile.shophouseapp.main.ui.screens.selectmenu.SimpleSelectMenu
import com.fabian.mobile.shophouseapp.main.ui.screens.textfield.CustomBasicTextField
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun RegistrarseScreen(
    registrarseViewModel: RegistrarseViewModel,
    setHeaderParams: (screen: ScreensEnum, title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit
) {
    val loading by registrarseViewModel.loading.collectAsState()

    val tiposidentificacion by registrarseViewModel.tiposidentificacion.collectAsState()
    val seleccionado by registrarseViewModel.seleccionado.collectAsState()
    val identificacion by registrarseViewModel.identificacion.collectAsState()
    val nombre by registrarseViewModel.nombre.collectAsState()
    val email by registrarseViewModel.email.collectAsState()
    val password by registrarseViewModel.password.collectAsState()
    val enabledButton by registrarseViewModel.enabledButton.collectAsState()
    Screen(
        tiposIdentificacion = tiposidentificacion,
        seleccionado = seleccionado,
        identificacion = identificacion,
        nombre = nombre,
        email = email,
        password = password,
        loading = loading,
        enabledButton = enabledButton,
        onValueChange = {
            registrarseViewModel.onValueChange(value = it)
        }, onClick = {
            registrarseViewModel.onClick(value = it as Tipoidentificacion)
        },
        onIdentificacionChange = {
            registrarseViewModel.identificacionChange(it)
        },
        onNombreChange = {
            registrarseViewModel.nombreChange(it)
        },
        onEmailChange = {
            registrarseViewModel.emailChange(it)
        },
        onPasswordChange = {
            registrarseViewModel.passwordChange(it)
        }, registrarOnClick = {
            registrarseViewModel.registrar()
        })

    val showSuccessDialog by registrarseViewModel.showSuccessDialog.collectAsState()
    OneButtonDialog(
        show = showSuccessDialog,
        message = "registro exitoso",
        onDismissRequest = {
            registrarseViewModel.dismissSuccessDialog()
        })
    LaunchedEffect(key1 = Unit) {
        setHeaderParams(ScreensEnum.Registrar, "Registrarse", "White", true, false, false, false)
        registrarseViewModel.cargarTiposidentificacion()
    }
}

@Composable
private fun Screen(
    tiposIdentificacion: List<Tipoidentificacion>,
    seleccionado: String,
    identificacion: String,
    nombre: String,
    email: String,
    password: String,
    loading: Boolean,
    enabledButton: Boolean,
    onValueChange: (String) -> Unit,
    onClick: (Any) -> Unit,
    onIdentificacionChange: (String) -> Unit,
    onNombreChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    registrarOnClick: () -> Unit

) {
    if (loading) {
        SimpleLoading(modifier = Modifier.fillMaxSize())
    } else {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 30.dp, bottom = 10.dp)
        ) {
            SimpleSelectMenu(
                label = "Seleccione tipo identificacion",
                fontSize = 15.sp,
                selectedItem = seleccionado,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                list = tiposIdentificacion,
                getStringValue = {
                    (it as Tipoidentificacion).nombre
                },
                onValueChange = { onValueChange(it) },
                onDismissRequest = { expanded = false },
                onClick = {
                    onClick(it)
                    expanded = false
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(percent = 50)),
                value = identificacion,
                onValueChange = onIdentificacionChange,
                textStyle = TextStyle(fontSize = 15.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                maxLines = 1,
                label = {
                    Text(
                        text = "Número de identificacion",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                })

            Spacer(modifier = Modifier.height(15.dp))
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(percent = 50)),
                value = nombre,
                onValueChange = onNombreChange,
                textStyle = TextStyle(fontSize = 15.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                maxLines = 1,
                label = {
                    Text(
                        text = "Nombre",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                })
            Spacer(modifier = Modifier.height(15.dp))
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(percent = 50)),
                value = email,
                onValueChange = onEmailChange,
                textStyle = TextStyle(fontSize = 15.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                maxLines = 1,
                label = {
                    Text(
                        text = "Correo electrónico",
                        color = Color.Gray,
                        fontSize = 15.sp
                    )
                })
            Spacer(modifier = Modifier.height(15.dp))
            var showPassword by rememberSaveable { mutableStateOf(false) }
            CustomBasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(percent = 50)),
                value = password,
                onValueChange = onPasswordChange,
                textStyle = TextStyle(fontSize = 15.sp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
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
                        fontSize = 15.sp
                    )
                }, visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            ButtonGeneral(
                modifier = Modifier.fillMaxWidth(),
                enabled = enabledButton,
                value = "Registrar",
                onCLick = registrarOnClick
            )
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
                tiposIdentificacion = emptyList(),
                seleccionado = "",
                identificacion = "",
                nombre = "",
                email = "",
                password = "",
                loading = false,
                enabledButton = true,
                onValueChange = {},
                onClick = {},
                onIdentificacionChange = {},
                onNombreChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                registrarOnClick = {}
            )
        }
    }
}