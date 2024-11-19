package com.example.kotlinanimemangaapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.kotlinanimemangaapp.presentation.ui.theme.Shapes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialogListAnimeForm(
    onDismiss: () -> Unit,
    onConfirm: (text: String) -> Unit
) {
    val valueInput = remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = Shapes.small,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                // Champ de recherche
                OutlinedTextField(
                    value = valueInput.value,
                    onValueChange = { newValue -> valueInput.value = newValue },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Titre") },
                )
                // Bouton en bas à droite du dialogue
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            writeListAnimeInFirestore(valueInput.value)
                        }
                ) {
                    Text(
                        text = "Annuler",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                onDismiss()
                            }
                    )
                    Text(
                        text = "Créer",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                onConfirm(valueInput.value)
                            }
                    )
                }
            }
        }
    }
}

fun writeListAnimeInFirestore(value: String) {

    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    val user = FirebaseAuth.getInstance().currentUser
    val list = hashMapOf(
        "id_user" to (user?.uid ?: ""),
        "shared" to false,
        "name" to value,
        "anime" to listOf(1, 3)
    )

    firestore
        .collection("list_anime_user")
        .add(list)
        .addOnSuccessListener { documentReference ->
            println("DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding document ${e.message}")
        }
}