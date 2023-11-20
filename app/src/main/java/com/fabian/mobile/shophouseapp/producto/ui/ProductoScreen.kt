package com.fabian.mobile.shophouseapp.producto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fabian.mobile.shophouseapp.R
import com.fabian.mobile.shophouseapp.network.NetworkConstants
import com.fabian.mobile.shophouseapp.producto.Producto
import com.fabian.mobile.shophouseapp.ui.theme.DarkTransparent
import com.fabian.mobile.shophouseapp.ui.theme.Orange
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun ProductoScreen(
    productoViewModel: ProductoViewModel,
    categoriaId: Int?,
    categoriaNombre: String
) {

    val productos by productoViewModel.productos.collectAsState()
    Screen(productos = productos, categoriaNombre = categoriaNombre)
    LaunchedEffect(key1 = 1) {
        productoViewModel.cargarProductos(catId = categoriaId)
    }
}

@Composable
private fun Screen(productos: List<Producto>, categoriaNombre: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            text = categoriaNombre,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(space = 30.dp),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            productos.forEach { producto ->
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(10))) {

                        AsyncImage(
                            model = "${NetworkConstants.URL}producto/imagen/${producto.imagen}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color.Gray),
                            error = painterResource(id = R.drawable.error_load_image),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DarkTransparent),
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Text(
                                    modifier= Modifier
                                        .fillMaxWidth()
                                        .padding(start = 15.dp, end = 15.dp, top = 10.dp),
                                    text = producto.nombre,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 36.sp
                                )

                                Text(
                                    modifier= Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp, start = 15.dp, end = 15.dp),
                                    text = "Precio: $${producto.precioVenta}",
                                    fontSize = 24.sp,
                                    color = Color.White,
                                )

                                Box(modifier = Modifier.fillMaxSize()){
                                    Button(modifier = Modifier
                                        .clip(shape = RoundedCornerShape(50)).align(BottomCenter).padding(bottom = 10.dp), onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        containerColor = Orange
                                    )) {
                                        Text(text = "Añadir a la canasta", fontSize = 20.sp)
                                    }
                                }



                            }
                        }

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
            val productos = emptyList<Producto>()
            Screen(categoriaNombre = "", productos = productos)
        }
    }
}