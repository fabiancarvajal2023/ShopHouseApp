package com.fabian.mobile.shophouseapp.main.ui.screens.selectmenu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.cliente.Tipoidentificacion
import com.fabian.mobile.shophouseapp.main.ui.screens.textfield.CustomBasicTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSelectMenu(
    label: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    selectedItem: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    list: List<Any>,
    getStringValue: (Any) -> String,
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onClick: (Any) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {
            CustomBasicTextField(
                modifier = Modifier
                    .menuAnchor()
                    .clip(shape = RoundedCornerShape(50)),
                textStyle = TextStyle(fontSize = fontSize),
                value = selectedItem,
                onValueChange = onValueChange,
                readOnly = true,
                label = {
                    Text(text = label, fontSize = fontSize)
                },
                trailingIcon = {
                    if (!expanded) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier.size(22.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier
                                .rotate(180f)
                                .size(22.dp)
                        )
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = onDismissRequest
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(text = getStringValue(item), fontSize = fontSize)
                        },
                        onClick = {
                            onClick(item)
                        }
                    )
                }
            }
        }
    }
}


@Preview(name = "NEXUS_Small", widthDp = 320, heightDp = 534)
@Composable
private fun Preview() {
    val coffeeDrinks = listOf("hola", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(true) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
    SimpleSelectMenu(
        label = "Seleccione tipo identificacion",
        fontSize = 18.sp,
        selectedItem = selectedText,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        list = coffeeDrinks,
        getStringValue = {
            (it as Tipoidentificacion).nombre
        },
        onValueChange = {},
        onDismissRequest = { expanded = false },
        onClick = {
            selectedText = it as String
            expanded = false
        }
    )
}