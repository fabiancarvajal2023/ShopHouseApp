package com.fabian.mobile.shophouseapp.main.ui.screens.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SimpleLoading(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}