package com.example.kotlinanimemangaapp.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinanimemangaapp.presentation.components.buttons.LoginButton
import com.example.kotlinanimemangaapp.presentation.navigation.nav_graph.Graph
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
    navController: NavController
) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = Firebase.auth

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email field
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { androidx.compose.material.Text("Email") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { androidx.compose.material.Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sign in button
        LoginButton(onClicked = {
            val email = emailState.value.trim()
            val password = passwordState.value

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    context,
                    "L'email et le mot de passe ne peuvent pas être vides.",
                    Toast.LENGTH_SHORT
                ).show()
                return@LoginButton
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Connexion réussie.",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(Graph.MAIN_ROUTE)
                    } else {
                        // If sign in fails, display a message to the user
                        Toast.makeText(
                            context,
                            "Erreur de connexion.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // updateUI(null)
                    }
                }

        })

        Spacer(modifier = Modifier.height(16.dp))

        // Reset password button
        Button(
            onClick = {
                val email = emailState.value.trim()

                if (email.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Email cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Password reset email sent.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context,
                                "Failed to send password reset email.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            androidx.compose.material.Text(text = "Réinitialiser le mot de passe")
        }

        // Create button
        Button(
            onClick = {
                val email = emailState.value.trim()
                val password = passwordState.value

                if (email.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Email cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Authentication succeeded.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val user = auth.currentUser
                            if (user != null) {
                                user.sendEmailVerification()
                            }
                            navController.navigate(Graph.MAIN_ROUTE)
                            // updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(
                                context,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // updateUI(null)
                        }
                    }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            androidx.compose.material.Text(text = "Créer un compte")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}