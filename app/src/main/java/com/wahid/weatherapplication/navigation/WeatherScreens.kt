package com.wahid.weatherapplication.navigation

//Directory Screen in project
enum class WeatherScreens {
    SplashScreen,
    MainScreen,
    LoginScreen,
    CreateAccountScreen,
    AboutScreen,
    FavoriteScreen,
    SearchScreen,
    SettingScreen;

    companion object {
        fun fromRoute(route: String?): WeatherScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> CreateAccountScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}