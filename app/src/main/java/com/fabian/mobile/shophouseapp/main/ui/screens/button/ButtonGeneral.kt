package com.fabian.mobile.shophouseapp.main.ui.screens.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.ui.theme.Orange

@Composable
fun ButtonGeneral(modifier: Modifier,value: String, onCLick: () -> Unit) {
    Button(
        modifier = modifier.clip(shape = RoundedCornerShape(50)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange,
            contentColor = Color.White
        ), onClick = onCLick
    ) {
        Text(text = value, fontSize = 18.sp)
    }
}