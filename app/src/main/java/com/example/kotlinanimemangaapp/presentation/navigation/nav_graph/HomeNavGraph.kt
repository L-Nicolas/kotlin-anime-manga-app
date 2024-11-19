package com.example.kotlinanimemangaapp.presentation.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kotlinanimemangaapp.presentation.screens.BottomBarScreen
import com.example.kotlinanimemangaapp.presentation.screens.DETAIL_ARGUMENT_KEY
import com.example.kotlinanimemangaapp.presentation.screens.profil.ProfileScreen
import com.example.kotlinanimemangaapp.presentation.screens.Screen
import com.example.kotlinanimemangaapp.presentation.screens.anime_list.AnimeListScreen
import com.example.kotlinanimemangaapp.presentation.screens.anime_detail.AnimeDetailsScreen
import com.example.kotlinanimemangaapp.presentation.screens.social.SocialScreen
import com.example.kotlinanimemangaapp.presentation.screens.user_list_animes_detail.UserAnimeListDetailScreen
import com.example.kotlinanimemangaapp.presentation.screens.user_liste_animes.UserAnimeListScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME_ROUTE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            AnimeListScreen(navController);
        }
        composable(route = BottomBarScreen.UserList.route) {
            UserAnimeListScreen(navController)
        }
        composable(route = BottomBarScreen.Search.route) {
            SocialScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            AnimeDetailsScreen(navController = navController,)
        }
        composable(
            route = Screen.UserListDetail.route,
            arguments = listOf(
                navArgument(DETAIL_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){
            UserAnimeListDetailScreen(navController = navController)
        }
    }
}