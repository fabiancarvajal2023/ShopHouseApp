package com.fabian.mobile.shophouseapp.main.ui.screens.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OneButtonDialog(
    show: Boolean,
    message: String,
    onDismissRequest: () -> Unit,
) {
    if (show) {
        AlertDialog(onDismissRequest = onDismissRequest,
            text = { Text(text = message, color = Color.Black) },
            confirmButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "aceptar", color = Color.Black)
                }
            }
        )
    }
}
