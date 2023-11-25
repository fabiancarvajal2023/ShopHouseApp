package com.fabian.mobile.shophouseapp.carro.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.ScreensEnum
import com.fabian.mobile.shophouseapp.carro.ProductoCarrito
import com.fabian.mobile.shophouseapp.cliente.constants.ClienteConstants
import com.fabian.mobile.shophouseapp.main.ui.screens.button.ButtonGeneralArrowEnd
import com.fabian.mobile.shophouseapp.main.ui.screens.dialogs.OneButtonDialog
import com.fabian.mobile.shophouseapp.main.ui.screens.loading.SimpleLoading
import com.fabian.mobile.shophouseapp.main.util.Preferences
import com.fabian.mobile.shophouseapp.ui.theme.Dark
import com.fabian.mobile.shophouseapp.ui.theme.Orange
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun CarritoScreen(
    carritoViewModel: CarritoViewModel,
    setHeaderParams: (screen: ScreensEnum, title: String, color: String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit,
    onBack: () -> Unit,
    onLogin:()->Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = 1)
    {
        setHeaderParams(
            ScreensEnum.Carrito,
            "Canasta",
            "White",
            true,
            false,
            false,
            true
        )
    }

    val productosCarrito by carritoViewModel.productosCarrito.collectAsState()
    val total by carritoViewModel.total.collectAsState()
    val numeroProductos by carritoViewModel.numeroProductos.collectAsState()
    val isLoading by carritoViewModel.isLoading.collectAsState()
    Screen(
        productosCarrito = productosCarrito,
        total = total,
        numeroProductos = numeroProductos,
        isLoading = isLoading,
        onBack = onBack,
        aumentar = {
            carritoViewModel.aumentar(it)
        },
        disminuir = {
            carritoViewModel.disminuir(it)
        },
        hacerPeidoOnclick = {
            carritoViewModel.hacerPedido()
        }
    )

    val showErrorDialog by carritoViewModel.showSuccessDialog.collectAsState()
    OneButtonDialog(
        show = showErrorDialog,
        message = "!Hemos recibido tu pedido¡, pronto te informaremos sobre el estado de tu pedido.",
        onDismissRequest = {
            carritoViewModel.dismissSuccessDialog()
            onBack()
        })

    LaunchedEffect(key1 = 1) {
        carritoViewModel.cargarValues()
        val preference = Preferences(context = context)
        val id = preference.getLong(ClienteConstants.ID)
        carritoViewModel.setId(id)
    }

    val login by carritoViewModel.login.collectAsState()
    LaunchedEffect(key1 = login){
        if(login){
            carritoViewModel.resetLogin()
            onLogin()
        }
    }
}


@Composable
private fun Screen(
    productosCarrito: List<ProductoCarrito>,
    total: Int,
    isLoading:Boolean,
    numeroProductos: Int,
    onBack: () -> Unit,
    aumentar: (Long) -> Unit,
    disminuir: (Long) -> Unit,
    hacerPeidoOnclick: () -> Unit
) {
    if (productosCarrito.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "No hayt productos agregados",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 30.sp,
                color = Color.White
            )
        }
    } else {
        if(isLoading){
            SimpleLoading(modifier = Modifier.fillMaxSize())
        }else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Total: $$total",
                        fontSize = 26.sp,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Productos: $numeroProductos",
                        fontSize = 26.sp,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(fraction = 0.60f)
                        .fillMaxWidth()
                ) {
                    productosCarrito.forEach() {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(top = 10.dp)
                                    .clip(shape = RoundedCornerShape(10))
                                    .background(
                                        Dark
                                    )
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1.3f)
                                        .align(CenterVertically)
                                        .padding(start = 10.dp),
                                    text = if (it.producto.nombre.length > 9) {
                                        it.producto.nombre.substring(0, 8)
                                    } else {
                                        it.producto.nombre
                                    },
                                    color = Color.White,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp
                                )
                                Row(
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(CenterVertically),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    IconButton(onClick = { disminuir(it.producto.id) }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBackIos,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }
                                    Text(
                                        modifier = Modifier.align(CenterVertically),
                                        text = "${it.cantidad}",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontSize = 21.sp
                                    )

                                    IconButton(onClick = { aumentar(it.producto.id) }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowForwardIos,
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                    }

                                }
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(CenterVertically)
                                        .padding(end = 10.dp),
                                    text = "$${(it.producto.precioVenta * it.cantidad)}",
                                    color = Color.White,
                                    textAlign = TextAlign.End,
                                    fontSize = 21.sp
                                )
                            }
                        }
                    }
                }
                Text(
                    text = "Mas productos",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(50))
                        .background(
                            Orange
                        )
                        .align(CenterHorizontally)
                        .clickable {
                            onBack()
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .size(30.dp)
                            .align(Center),
                        imageVector = Icons.Filled.ShoppingCart,
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(Orange)
                            .align(
                                BottomCenter
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        ButtonGeneralArrowEnd(
                            modifier = Modifier,
                            value = "Hacer pedido",
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Dark,
                                contentColor = Color.White,
                                disabledContainerColor = Color.Gray,
                                disabledContentColor = Color.White,
                            ),
                            onCLick = {
                                hacerPeidoOnclick()
                            }
                        )
                    }
                }

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
            color = MaterialTheme.colorScheme.background
        ) {
            Screen(
                productosCarrito = emptyList(),
                total = 13000,
                numeroProductos = 4,
                isLoading = false,
                onBack = {},
                aumentar = {},
                disminuir = {},
                hacerPeidoOnclick = {}
            )
        }
    }
}