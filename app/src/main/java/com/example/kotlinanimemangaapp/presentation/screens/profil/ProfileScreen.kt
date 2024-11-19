package com.example.kotlinanimemangaapp.presentation.screens.profil

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kotlinanimemangaapp.presentation.StartActivity
import com.example.kotlinanimemangaapp.presentation.components.buttons.LoginButton
import com.example.kotlinanimemangaapp.presentation.components.bar.BottomBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfilViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val auth = Firebase.auth
    Scaffold(
        topBar = { ProfilAppBar() },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoginButton(
                text = "Se déconnecter",
                loadingText = "Déconnexion...",
                backgroundColor = Color(0xFFFF0000),
                textColor = Color(0xFFFFFFFF),
                onClicked = {
                    auth.signOut()
                    val intent = Intent(context, StartActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun ProfilAppBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                androidx.compose.material.Text(
                    text = "Anime",
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}