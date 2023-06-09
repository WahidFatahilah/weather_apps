package com.wahid.weatherapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController,
    elevation: Dp = 0.dp,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
)
{
    TopAppBar(
        title = { Text(
            text = title,
            color = MaterialTheme.colors.onSecondary,
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 15.sp
        )},
        actions = {
           if(isMainScreen){
               IconButton(onClick = { onAddActionClicked.invoke()}) {
                   Icon(imageVector =
                   Icons.Default.Search,
                       contentDescription = "Search Icon")

               }

               IconButton(onClick = { /*TODO*/ }) {
                   Icon(imageVector =
                   Icons.Rounded.MoreVert,
                       contentDescription = "More Icon" )

               }
           }
        },
        navigationIcon = {
            if(icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    }
                )

            }

        },
        backgroundColor = Color.DarkGray,
        elevation = elevation
    )
}