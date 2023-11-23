package com.fabian.mobile.shophouseapp.categoria.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.fabian.mobile.shophouseapp.R
import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.categoria.Promocion
import com.fabian.mobile.shophouseapp.network.NetworkConstants
import com.fabian.mobile.shophouseapp.ui.theme.DarkTransparent
import com.fabian.mobile.shophouseapp.ui.theme.LightTransparent
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme
import kotlinx.coroutines.delay

@Composable
fun CategoriaScreen(
    categoriaViewModel: CategoriaViewModel,
    setHeaderParams: (title: String,color:String, mostrarBotonAtras: Boolean, mostrarCajaBusqueda: Boolean, mostrarCarroCompras: Boolean, mostrarRegistrarUsuario: Boolean) -> Unit,
    onClickCategoria: (Int, String) -> Unit
) {

    val context = LocalContext.current
    val categorias by categoriaViewModel.categorias.collectAsState()
    val promociones by categoriaViewModel.promociones.collectAsState()
    Screen(categorias = categorias, promociones = promociones, onClickCategoria = onClickCategoria)

    LaunchedEffect(key1 = 1) {
        setHeaderParams(context.getString(R.string.app_name), "Black",false, true, true, true)
        categoriaViewModel.cargarCategorias()
        categoriaViewModel.cargarPromociones()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Screen(
    categorias: List<Categoria>,
    promociones: List<Promocion>,
    onClickCategoria: (Int, String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        var mostrarOfertas by rememberSaveable { mutableStateOf(true) }
        Row(
            modifier = Modifier.padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Oferta",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { mostrarOfertas = !mostrarOfertas }) {
                if (mostrarOfertas) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
                } else {
                    Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        val pagerState = rememberPagerState(pageCount = { promociones.size })
        AnimatedVisibility(visible = mostrarOfertas) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp), state = pagerState
            ) { currentPage ->

                val promocion = promociones[currentPage]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(10))
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = "${NetworkConstants.URL}categoria/imagen/${promocion.categoria.imagen}",
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray),
                        error = painterResource(id = R.drawable.error_load_image),
                        contentScale = ContentScale.FillBounds
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(LightTransparent)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                    ) {

                        val color = Color.Black
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "${promocion.porcentaje}%",
                            textAlign = TextAlign.Center,
                            fontSize = 45.sp,
                            fontWeight = FontWeight.Bold,
                            color = color
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "Descuento en",
                            textAlign = TextAlign.Center,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Medium,
                            color = color
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = if (promocion.articuloRequerido) {
                                "productos de ${promocion.categoria.nombre}"
                            } else {
                                "productos ${promocion.categoria.nombre}"
                            },
                            textAlign = TextAlign.Center,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Medium,
                            color = color
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp),
            text = "Categorias",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .pointerInput(key1 = Unit) {
                    detectTapGestures(onPress = {
                        if (mostrarOfertas) {
                            mostrarOfertas = false
                        }
                    })
                }
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
            columns = GridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
            verticalArrangement = Arrangement.spacedBy(space = 10.dp),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            categorias.forEach() { categoria ->
                item {
                    Box(
                        modifier = Modifier
                            .pointerInput(key1 = Unit) {
                                detectTapGestures(onTap = {
                                    onClickCategoria(categoria.id, categoria.nombre)
                                }, onPress = {
                                    if (mostrarOfertas) {
                                        mostrarOfertas = false
                                    }
                                })
                            }
                            .aspectRatio(1f)
                            .clip(shape = RoundedCornerShape(10))
                    ) {
                        AsyncImage(
                            model = "${NetworkConstants.URL}categoria/imagen/${categoria.imagen}",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray),
                            error = painterResource(id = R.drawable.error_load_image),
                            contentScale = ContentScale.FillBounds
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DarkTransparent),
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = "producto",
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = categoria.nombre,
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(3000)
                val siguientePagina = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.scrollToPage(page = siguientePagina)
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
            val categorias: List<Categoria> = emptyList()
            val promociones: List<Promocion> = emptyList()
            Screen(
                categorias = categorias,
                promociones = promociones,
                onClickCategoria = { _, _ ->

                }
            )
        }
    }
}