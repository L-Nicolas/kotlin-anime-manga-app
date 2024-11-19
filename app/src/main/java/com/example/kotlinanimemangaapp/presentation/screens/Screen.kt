package com.example.kotlinanimemangaapp.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

const val DETAIL_ARGUMENT_KEY = "id"
sealed class Screen(val route: String) {
    object AnimatedSplashScreen : Screen("splash_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Details : Screen("details_screen?id={id}") {
        fun passId(
            id: Int = 0
        ): String {
            return "details_screen?id=$id"
        }
    }

    object UserListDetail : Screen("user_list_detail_screen?id={id}"){
        fun passId(
            id: String = ""
        ): String {
            return "user_list_detail_screen?id=$id"
        }
    }
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")
    object Profil : Screen("profile_screen")
}

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : BottomBarScreen(
        route = "list_screen",
        icon = Icons.Default.Home,
        title = "Home"
    )
    object UserList : BottomBarScreen(
        route = "user_anime_list_screen",
        icon = Icons.Default.List,
        title = "User Anime"
    )
    object Search : BottomBarScreen(
        route = "search_screen",
        icon = Icons.Default.Person,
        title = "Search"
    )
    object Profile : BottomBarScreen(
        route = "profile_screen",
        icon = Icons.Default.Settings,
        title = "Profile"
    )
}


sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}
