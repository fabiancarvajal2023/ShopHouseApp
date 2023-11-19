package com.fabian.mobile.shophouseapp.categoria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.fabian.mobile.shophouseapp.categoria.Categoria
import com.fabian.mobile.shophouseapp.network.NetworkConstants
import com.fabian.mobile.shophouseapp.ui.theme.DarkTransparent
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun CategoriaScreen(categoriaViewModel: CategoriaViewModel) {

    val categorias by categoriaViewModel.categorias.collectAsState()
    Screen(categorias = categorias)

    LaunchedEffect(key1 = 1) {
        categoriaViewModel.loadCategorias()
    }
}

@Composable
private fun Screen(categorias: List<Categoria>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Categorias", fontSize = 24.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
            verticalArrangement = Arrangement.spacedBy(space = 10.dp),
            contentPadding = PaddingValues(all = 0.dp)
        ) {
            categorias.forEach() { categoria ->
                item {
                    Box(modifier = Modifier.aspectRatio(1f).clip(shape = RoundedCornerShape(10))) {
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
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    text = categoria.nombre,
                                    textAlign = TextAlign.Center,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
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
            val categorias: List<Categoria> = emptyList()
            Screen(
                categorias = categorias
            )
        }
    }
}