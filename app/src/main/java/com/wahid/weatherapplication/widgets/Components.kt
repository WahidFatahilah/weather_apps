package com.wahid.weatherapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wahid.weatherapplication.model.Weather
import com.wahid.weatherapplication.navigation.WeatherScreens
import com.wahid.weatherapplication.ui.theme.ButtonBlue
import com.wahid.weatherapplication.utils.formatDate
import com.wahid.weatherapplication.utils.formatDateTime
import com.wahid.weatherapplication.widgets.HumidityWindPressureRow
import com.wahid.weatherapplication.widgets.WeatherStateImage


@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)


}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value,
        onValueChange = { valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction)


}

@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icons.Default.Close

    }

}

@Composable
fun ShowWeather(
    weather: Weather,
    imageUrl: String,
    celcius: Int,
    navController: NavController,
    defaultCity: String,
    weatherNewYork: Weather,
    weatherSingapore: Weather,
    weatherMumbai: Weather,
    weatherDelhi: Weather,
    weatherSydney: Weather,
    weatherMelbourne: Weather,
) {
    Column(
        Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFF10428D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier,
            color = Color(0xFF097887),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 40.dp,
                bottomStart = 40.dp
            )
        )
        {
            Column(
                Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "Last Update")
                Text(
                    text = formatDateTime(weather.list[0].dt),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = (15.dp))
                )
                Surface(
                    modifier = Modifier
                        .padding(top = (4.dp), end = (10.dp), bottom = (10.dp), start = (10.dp))
                        .size(200.dp), shape = CircleShape, color = Color(color = 0xFF097887)
                )
                {
                    Column(
                        modifier = Modifier.size(200.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        Column(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(200.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            WeatherStateImage(imageUrl = imageUrl.toString())
                        }
                    }
                }

                Text(
                    text = "$celcius°C",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = weather.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic
                )
                Divider(color = Color.White)
                HumidityWindPressureRow(weather = weather.list[0])
                Button(onClick = { navController.navigate(WeatherScreens.MainScreen.name + "/$defaultCity") }) {
                    Text(text = "Refresh")
                }
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFF10428D),

            )
        {
            Column(
                Modifier
                    // .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {

                ShowCityWeather(
                    weatherNewYork,
                    weatherSingapore,
                    weatherMumbai,
                    weatherDelhi,
                    weatherSydney,
                    weatherMelbourne
                )
            }
        }
    }
}

@Composable
fun ShowCityWeather(
    weatherNewYork: Weather,
    weatherSingapore: Weather,
    weatherMumbai: Weather,
    weatherDelhi: Weather,
    weatherSydney: Weather,
    weatherMelbourne: Weather,
) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "New York City")
                var imageUrlNewYork =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherNewYork.list[0].weather[0].icon}.json"
                var tempNewYork = weatherNewYork.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageUrlNewYork)
                var celciusNewYork: Int = tempNewYork - 273
                Text(text = weatherNewYork.list[0].weather[0].main)
                Text(text = "$celciusNewYork°C")
            }
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Singapore")
                var imageSingapore =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherSingapore.list[0].weather[0].icon}.json"
                var tempSingapore = weatherSingapore.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageSingapore)
                var celciusSingapore: Int = tempSingapore - 273
                Text(text = weatherSingapore.list[0].weather[0].main)
                Text(text = "$celciusSingapore°C")
            }
        }
    }

    Row(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Mumbai")
                var imageUrlMumbai =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherMumbai.list[0].weather[0].icon}.json"
                var tempMumbai = weatherMumbai.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageUrlMumbai)
                var celciusMumbai: Int = tempMumbai - 273
                Text(text = weatherMumbai.list[0].weather[0].main)
                Text(text = "$celciusMumbai°C")
            }
        }

        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Delhi")
                var imageDelhi =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherDelhi.list[0].weather[0].icon}.json"
                var tempDelhi = weatherDelhi.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageDelhi)
                var celciusDelhi: Int = tempDelhi - 273
                Text(text = weatherDelhi.list[0].weather[0].main)
                Text(text = "$celciusDelhi°C")
            }

        }

    }

    Row(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Sydney")
                var imageUrlSydney =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherSydney.list[0].weather[0].icon}.json"
                var tempSydney = weatherSydney.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageUrlSydney)
                var celciusSydney: Int = tempSydney - 273
                Text(text = weatherSydney.list[0].weather[0].main)
                Text(text = "$celciusSydney°C")
            }
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.clickable { },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Melbourne")
                var imageMelbourne =
                    "https://wahidftesting.000webhostapp.com/lottiefiles/${weatherMelbourne.list[0].weather[0].icon}.json"
                var tempMelbourne = weatherMelbourne.list[0].temp.eve.toInt()
                WeatherStateImage(imageUrl = imageMelbourne)
                var celciusMelbourne: Int = tempMelbourne - 273
                Text(text = weatherMelbourne.list[0].weather[0].main)
                Text(text = "$celciusMelbourne°C")
            }
        }
    }
}
