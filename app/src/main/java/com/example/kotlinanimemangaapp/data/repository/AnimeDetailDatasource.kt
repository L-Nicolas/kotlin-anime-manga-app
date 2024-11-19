package com.example.kotlinanimemangaapp.data.repository

import com.example.kotlinanimemangaapp.domain.model.AnimeDetail
import com.example.kotlinanimemangaapp.domain.repository.AnimeDetailRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AnimeDetailDatasource @Inject constructor(
    val animeDatasource: AnimeDatasource,
    val database: FirebaseFirestore,
) : AnimeDetailRepository {

    override suspend fun getAnimeDetail(idAnime: Int): AnimeDetail? {
        return try {
            val anime = animeDatasource.getAnime(idAnime)
            if (anime != null) {
                val liked = checkIfAnimeIsLiked(idAnime)
                val watched = checkIfAnimeIsInUserWatchList(idAnime)
                AnimeDetail(anime, liked, watched)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    private suspend fun checkIfAnimeIsLiked(idAnime: Int): Boolean {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val querySnapshot = database.collection("list_liked_anime")
            .whereEqualTo("id_user", userId)
            .whereArrayContains("anime", idAnime)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }

    private suspend fun checkIfAnimeIsInUserWatchList(idAnime: Int): Boolean {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val querySnapshot = database.collection("list_watched_anime")
            .whereEqualTo("id_user", userId)
            .whereArrayContains("anime", idAnime)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }


    //check if the anime is in user_list_anime document of the user
    private suspend fun checkIfAnimeIsInUserList(idAnime: Int): Boolean {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val querySnapshot = database.collection("list_anime_user")
            .whereEqualTo("id_user", userId)
            .whereEqualTo("anime", idAnime)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }
}