package com.example.kotlinanimemangaapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.model.Anime
import com.example.kotlinanimemangaapp.domain.model.AnimeListDetail
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.model.ListAnimeCreate
import com.example.kotlinanimemangaapp.domain.model.ListAnimeWatched
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ListAnimeDatasource @Inject constructor(
    val animeDatasource: AnimeDatasource,
    val database: FirebaseFirestore,
) : ListAnimeRepository {

    override suspend fun getListAnimeShared(): List<ListAnime> {
        val user = FirebaseAuth.getInstance().currentUser

        return suspendCoroutine { continuation ->
            database
                .collection("list_anime_user")
                .whereEqualTo("shared", true)
                //.whereNotEqualTo("id_user", user?.uid)
                .get()
                .addOnSuccessListener { documents ->
                    val animeList = mutableStateListOf<ListAnime>()
                    for (document in documents) {
                        val listAnime = ListAnime(
                            anime = document.data["anime"] as List<Int>,
                            name = document.data["name"] as String,
                            id_user = document.data["id_user"] as String,
                            id = document.id
                        )
                        animeList.add(listAnime)
                    }
                    continuation.resume(animeList.toList())
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }

    override suspend fun getListAnimeOfUser(idList: String): AnimeListDetail {
        var resultList = AnimeListDetail(
            animes = mutableListOf(),
            title = "",
            id_user = "",
            shared = false,
            id = ""
        )

        try {
            val documentSnapshot = database.collection("list_anime_user")
                .document(idList)
                .get()
                .await()

            if (documentSnapshot.exists()) {
                val title = documentSnapshot.getString("title") ?: ""
                val idUser = documentSnapshot.getString("id_user") ?: ""
                val shared = documentSnapshot.getBoolean("shared") ?: false
                val id = documentSnapshot.id
                val listAnime = mutableListOf<Anime>()

                val anime = documentSnapshot.get("anime") as? List<Int> ?: emptyList()

                resultList.title = title
                resultList.id_user = idUser
                resultList.id = id
                resultList.shared = shared

                anime.forEach { idAnime ->
                    val anime = animeDatasource.getAnime(idAnime)
                    if (anime != null) {
                        listAnime.add(anime)
                    }
                }
                resultList.animes = listAnime
            }
        } catch (e: Exception) {
            // Gérez les exceptions ici si nécessaire
        }

        return resultList
    }

    override suspend fun getListAnimeOfUser(): List<ListAnime> {
        val user = FirebaseAuth.getInstance().currentUser

        return suspendCoroutine { continuation ->
            database
                .collection("list_anime_user")
                .whereEqualTo("id_user", user?.uid)
                .get()
                .addOnSuccessListener { documents ->
                    val animeList = mutableStateListOf<ListAnime>()
                    for (document in documents) {
                        var animes: List<Int> = emptyList()
                        if(document.data["anime"] == null) {
                            animes = document.data["anime"] as List<Int>
                        }

                        val listAnime = ListAnime(
                            anime = animes,
                            name = document.data["name"] as String,
                            id_user = document.data["id_user"] as String,
                            id = document.id
                        )
                        animeList.add(listAnime)
                    }
                    continuation.resume(animeList.toList())
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }

    override suspend fun getListAnimeWatchedOfUser() : List<ListAnime> {
        val user = FirebaseAuth.getInstance().currentUser
        return suspendCoroutine { continuation ->
            if (user != null) {
                database
                    .collection("list_watched_anime")
                    .whereEqualTo("id_user", user.uid)
                    .get()
                    .addOnSuccessListener { documents ->
                        val animeList = mutableStateListOf<ListAnime>()
                        for (document in documents) {
                            val listAnime = ListAnime(
                                anime = document.data["anime"] as List<Int>,
                                name = "Vu",
                                id_user = user.uid,
                                id = document.id
                            )
                            animeList.add(listAnime)
                        }
                        continuation.resume(animeList.toList())
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        }
    }


    override suspend fun getListFavoriteAnimeOfUser() : List<ListAnime> {
        val user = FirebaseAuth.getInstance().currentUser
        return suspendCoroutine { continuation ->
            if (user != null) {
                database
                    .collection("list_liked_anime")
                    .whereEqualTo("id_user", user.uid)
                    .get()
                    .addOnSuccessListener { documents ->
                        val animeList = mutableStateListOf<ListAnime>()
                        for (document in documents) {
                            val listAnime = ListAnime(
                                anime = document.data["anime"] as List<Int>,
                                name = "Favorite",
                                id_user = user.uid,
                                id = document.id
                            )
                            animeList.add(listAnime)
                        }
                        continuation.resume(animeList.toList())
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            }
        }
    }

    override suspend fun shareList(id: String): Resource<Unit> {
        try {
            val documentSnapshot = database
                .collection("list_anime_user")
                .document(id)
                .get()
                .await()

            if (documentSnapshot.exists()) {
                val existingAnime = documentSnapshot.get("shared") as? Boolean ?: false

                database.collection("list_anime_user")
                    .document(id)
                    .update("shared", !existingAnime)
                    .await()

                return Resource.Success(Unit)
            } else {
                return Resource.Error(message = "You can change the status.")
            }
        } catch (e: Exception) {
            return Resource.Error(message = "Error updating document: ${e.message}")
        }
    }

    override suspend fun addAnimeToList(idAnime: Int, idList: String): Resource<Unit> {
        try {
            val documentSnapshot = database
                .collection("list_anime_user")
                .document(idList)
                .get()
                .await()

            // Vérifie si le document existe pour l'utilisateur et la liste spécifique
            if (documentSnapshot.exists()) {
                // Récupère la liste de animes existante dans le document
                val existingAnime = documentSnapshot.get("anime") as? List<Int> ?: emptyList()

                // Vérifie si l'ID du anime est déjà présent dans la liste
                if (existingAnime.any { it == idAnime }) {
                    return Resource.Error(message = "Anime already in list")
                } else {
                    // Si l'ID du anime n'est pas déjà dans la liste, ajoute-le
                    val updatedAnime = existingAnime + idAnime

                    // Met à jour le document avec la liste de animes mise à jour
                    database.collection("list_anime_user")
                        .document(idList)
                        .update("anime", updatedAnime)
                        .await()
                }
            } else {
                // Si le document n'existe pas, renvoie une erreur indiquant que la liste est introuvable
                return Resource.Error(message = "List not found")
            }

            return Resource.Success(Unit)
        } catch (e: Exception) {
            return Resource.Error(message = "Error adding anime in list: ${e.message}")
        }
    }
    override suspend fun addAnimeToWatched(idAnime: Int): Resource<Unit> {
        val user = FirebaseAuth.getInstance().currentUser

        try {
            val documentSnapshot = database
                .collection("list_watched_anime")
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
                    database.collection("list_watched_anime")
                        .document(documentId)
                        .update("anime", updatedAnime)
                        .await()
                } else {
                    // Si l'ID du anime n'est pas déjà dans la liste, ajoute-le
                    val updatedAnime = existingAnime + idAnime

                    // Met à jour le document avec la liste de animes mise à jour
                    database.collection("list_watched_anime")
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

                database.collection("list_watched_anime")
                    .add(listAnime)
                    .await()
            }

            return Resource.Success(Unit)
        } catch (e: Exception) {
            return Resource.Error(message = "Error creating/updating document: ${e.message}")
        }
    }

    override suspend fun addList(title: String): Resource<Unit> {
        val user = FirebaseAuth.getInstance().currentUser

        try {
            val listAnime = ListAnimeCreate(
                name = title,
                id_user = user?.uid ?: "",
            )

            database.collection("list_anime_user")
                .add(listAnime)
                .await()

            return Resource.Success(Unit)
        } catch (e: Exception) {
            return Resource.Error(message = "Error creating document: ${e.message}")
        }
    }

    override suspend fun deleteList(idList: String): Resource<Unit> {
        try {
            database.collection("list_anime_user")
                .document(idList)
                .delete()
                .await()

            return Resource.Success(Unit)
        } catch (e: Exception) {
            return Resource.Error(message = "Error updating document: ${e.message}")
        }
    }

    override suspend fun deleteAnimeFromList(idList: String, idAnime: Int): Resource<Unit> {
        try {
            val documentSnapshot = database
                .collection("list_anime_user")
                .document(idList)
                .get()
                .await()

            if (documentSnapshot.exists()) {
                val existingAnime = documentSnapshot.get("anime") as? List<Int> ?: emptyList()

                val newAnime = existingAnime.filter { it != idAnime }

                database.collection("list_anime_user")
                    .document(idList)
                    .update("anime", newAnime)
                    .await()

                return Resource.Success(Unit)
            } else {
                return Resource.Error(message = "You can change the status.")
            }
        } catch (e: Exception) {
            return Resource.Error(message = "Error updating document: ${e.message}")
        }
    }
}