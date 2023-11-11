package com.fabian.mobile.shophouseapp.main.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabian.mobile.shophouseapp.R
import com.fabian.mobile.shophouseapp.main.ui.screens.search.SearchBar
import com.fabian.mobile.shophouseapp.ui.theme.Orange
import com.fabian.mobile.shophouseapp.ui.theme.ShopHouseAppTheme

@Composable
fun MainScreen(content: @Composable BoxScope.() -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Orange),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .shadow(elevation = 10.dp, shape = CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null
            )
            SearchBar(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(40.dp),
                searchText = "",
                isEmpty = false,
                onValueChange = {

                },
                onEmptyOnClick = { /*TODO*/ }) {

            }
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .shadow(elevation = 10.dp, shape = CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_person_add),
                contentDescription = null,
                contentScale = ContentScale.None
            )

            Image(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .shadow(elevation = 10.dp, shape = CircleShape)
                    .background(Color.White)
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_car),
                contentDescription = null,
                contentScale = ContentScale.None
            )
        }
        Box(Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShopHouseAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen() {

            }
        }
    }
}