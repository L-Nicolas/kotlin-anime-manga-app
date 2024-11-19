package com.example.kotlinanimemangaapp.data.repository

import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.ListAnimeWatched
import com.example.kotlinanimemangaapp.domain.repository.AnimeSavedRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AnimeSavedDatasource @Inject constructor(
    val database: FirebaseFirestore,
) : AnimeSavedRepository {

    override suspend fun addAnimeToFavorite(idAnime: Int): Resource<Unit> {
        val user = FirebaseAuth.getInstance().currentUser

        try {
            val documentSnapshot = database
                .collection("list_liked_anime")
                .whereEqualTo("id_user", user?.uid)
                .get()
                .await()

            // Vérifie si le document existe pour l'utilisateur
            if (!documentSnapshot.isEmpty) {
                // Si le document existe, récupère l'ID du document
                val documentId = documentSnapshot.documents[0].id

                // Récupère la liste de animes existante dans le document
                val existingAnime = documentSnapshot.documents[0].get("anime") as? List<Int> ?: emptyList()

                // Vérifie si l'ID du anime est déjà présent dans la liste
                if (existingAnime.any { it == idAnime }) {
                    // Si l'ID du anime est déjà dans la liste, supprime-le
                    val updatedAnime = existingAnime.filterNot { it == idAnime }

                    // Met à jour le document avec la liste de animes mise à jour
                    database.collection("list_liked_anime")
                        .document(documentId)
                        .update("anime", updatedAnime)
                        .await()
                } else {
                    // Si l'ID du anime n'est pas déjà dans la liste, ajoute-le
                    val updatedAnime = existingAnime + idAnime

                    // Met à jour le document avec la liste de animes mise à jour
                    database.collection("list_liked_anime")
                        .document(documentId)
                        .update("anime", updatedAnime)
                        .await()
                }
            } else {
                // Si le document n'existe pas, crée un nouveau document avec l'ID du anime
                val listAnime = ListAnimeWatched(
                    id_user = user?.uid ?: "",
                    anime = listOf(idAnime)
                )

                database.collection("list_liked_anime")
                    .add(listAnime)
                    .await()
            }

            return Resource.Success(Unit)
        } catch (e: Exception) {
            return Resource.Error(message = "Error creating/updating document: ${e.message}")
        }
    }
}
