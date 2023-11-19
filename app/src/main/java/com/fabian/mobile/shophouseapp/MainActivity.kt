package com.fabian.mobile.shophouseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaScreen
import com.fabian.mobile.shophouseapp.categoria.ui.CategoriaViewModel
import com.fabian.mobile.shophouseapp.main.ui.screens.MainScreen
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

class MainActivity : ComponentActivity() {

    private val categoriaViewModel: CategoriaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopHouseAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen() {

                        CategoriaScreen(categoriaViewModel = categoriaViewModel)
                    }
                }
            }
        }
    }
}