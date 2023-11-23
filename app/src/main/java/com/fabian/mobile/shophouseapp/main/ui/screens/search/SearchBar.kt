package com.fabian.mobile.shophouseapp.main.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabian.mobile.shophouseapp.main.ui.screens.textfield.CustomBasicTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    searchText: String,
    isEmpty: Boolean,
    onValueChange: (String) -> Unit,
    onEmptyOnClick: () -> Unit,
    onCloseOnClick: () -> Unit
) {
    CustomBasicTextField(
        modifier = modifier.clip(RoundedCornerShape(percent = 50)),
        value = searchText,
        onValueChange = onValueChange,
        textStyle = TextStyle(fontSize = 18.sp),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        trailingIcon = {
            if (isEmpty) {
                IconButton(
                    onClick = onCloseOnClick, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = ""
                    )
                }
            } else {
                IconButton(
                    onClick = onEmptyOnClick, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Gray,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = "Buscar",
                color = Color.Gray,
                fontSize = 18.sp
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        contentPadding = PaddingValues(
            start = 15.dp,
            end = 10.dp,
            top = 0.dp,
            bottom = 0.dp
        )
    )
}

@Preview
@Composable
private fun preview()
{
    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            searchText = "",
            isEmpty = true,
            onValueChange = {},
            onEmptyOnClick = { /*TODO*/ }) {

        }
    }

}