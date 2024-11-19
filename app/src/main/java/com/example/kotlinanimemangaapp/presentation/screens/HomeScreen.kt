package com.example.kotlinanimemangaapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinanimemangaapp.presentation.navigation.nav_graph.HomeNavGraph

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {
    HomeNavGraph(navController = navController)
}