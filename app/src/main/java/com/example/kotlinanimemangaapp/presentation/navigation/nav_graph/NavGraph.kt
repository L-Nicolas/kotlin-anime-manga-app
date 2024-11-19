package com.example.kotlinanimemangaapp.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinanimemangaapp.presentation.screens.AnimatedSplashScreen
import com.example.kotlinanimemangaapp.presentation.screens.HomeScreen
import com.example.kotlinanimemangaapp.presentation.screens.Screen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AnimatedSplashScreen.route,
        route = Graph.ROOT_ROUTE,
    ){
        composable(route = Screen.AnimatedSplashScreen.route) {
            AnimatedSplashScreen(navController = navController)
        }
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN_ROUTE) {
            HomeScreen()
        }
    }
}

object Graph {
    const val AUTHENTICATION_ROUTE = "authentication"
    const val ROOT_ROUTE = "root"
    const val MAIN_ROUTE = "main"
    const val HOME_ROUTE = "home"
}