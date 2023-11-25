package com.fabian.mobile.shophouseapp.main.ui.screens.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.ui.theme.Orange

@Composable
fun ButtonGeneral(
    modifier: Modifier,
    value: String,
    enabled: Boolean = true,
    onCLick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Orange,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.White,
    )
) {
    Button(
        modifier = modifier.clip(shape = RoundedCornerShape(50)),
        enabled = enabled,
        colors = colors, onClick = onCLick
    ) {
        Text(text = value, fontSize = 18.sp)
    }
}
@Composable
fun ButtonGeneralArrowEnd(
    modifier: Modifier,
    value: String,
    enabled: Boolean = true,
    onCLick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Orange,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.White,
    )
) {
    Button(
        modifier = modifier.clip(shape = RoundedCornerShape(50)),
        enabled = enabled,
        colors = colors, onClick = onCLick
    ) {
        Text(text = value, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(5.dp))
        Icon(modifier= Modifier.size(20.dp),imageVector = Icons.Filled.ArrowForwardIos, contentDescription ="", tint = Color.White)
    }
}