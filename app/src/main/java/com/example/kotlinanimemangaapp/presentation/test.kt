package com.example.kotlinanimemangaapp.presentation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class test {
}

fun readDataFromFirestore() {
    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    firestore
        .collection("users")
        .document("A2zVFCDfsJ91rVbcUeLT")
        .get()
        .addOnSuccessListener { document ->
            try {
                if (document != null) {
                    document.data?.forEach { (key, value) ->
                        println("$key => $value")
                    }
                } else {
                    println("Error getting documents")
                }
            } catch (ex: Exception) {
                println("Error getting documents")
            }
        }
        .addOnFailureListener { e ->
            println("Error getting documents ${e.message}")
        }
}

fun getAllUsersFromFirestore() {
    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    firestore
        .collection("users")
        .get()
        .addOnSuccessListener { documents ->
            try {
                if (documents != null) {
                    for (document in documents) {
                        document.data?.forEach { (key, value) ->
                            println("$key => $value")
                        }
                    }
                } else {
                    println("Error getting documents")
                }
            } catch (ex: Exception) {
                println("Error getting documents")
            }
        }
        .addOnFailureListener { e ->
            println("Error getting documents ${e.message}")
        }
}

//fun to writer user in firestore
fun writeUserInFirestore() {
    val firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

    val user = hashMapOf(
        "name" to "Nicolas",
        "lastName" to "Gomez",
        "age" to 25
    )

    firestore
        .collection("users")
        .add(user)
        .addOnSuccessListener { documentReference ->
            println("DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding document ${e.message}")
        }
}
