package com.example.kotlinanimemangaapp.presentation.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.kotlinanimemangaapp.presentation.screens.Screen
import com.example.kotlinanimemangaapp.presentation.screens.login.LoginScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTHENTICATION_ROUTE,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            //ScreenContent(name = AuthScreen.SignUp.route) {}
        }
    }
}