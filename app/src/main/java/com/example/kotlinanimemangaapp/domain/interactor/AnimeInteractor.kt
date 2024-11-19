package com.example.kotlinanimemangaapp.domain.interactor

import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToFavoriteUC
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToListUC
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToWatchedUC
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddListUC
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteAnimeFromListUC
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteListUC
import com.example.kotlinanimemangaapp.domain.interactor.get_anime.GetAnimeDetailsUC
import com.example.kotlinanimemangaapp.domain.interactor.get_anime.GetAnimeUC
import com.example.kotlinanimemangaapp.domain.interactor.get_genres.GetGenresUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeShareddUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListAnimeWatchedOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListFavoriteAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.interactor.update_shared_list.UpdateSharedListUC
import javax.inject.Inject

data class AnimeInteractor @Inject constructor(
    val getAnimeDetailsUC: GetAnimeDetailsUC,
    val addAnimeToFavoriteUC: AddAnimeToFavoriteUC,
    val addAnimeToWatchedUC: AddAnimeToWatchedUC,
    val getListAnimeOfUserUC: GetListAnimeOfUserUC,
    val addAnimeToListUC: AddAnimeToListUC,
    val deleteAnimeFromListUC: DeleteAnimeFromListUC,
    val getAnimeUC: GetAnimeUC,
    val getGenresUC: GetGenresUC,
    val getListAnimeShareddUC: GetListAnimeShareddUC,
    val getListAnimeWatchedOfUserUC: GetListAnimeWatchedOfUserUC,
    val getListFavoriteAnimeOfUserUC: GetListFavoriteAnimeOfUserUC,
    val addListUC: AddListUC,
    val updateSharedListUC: UpdateSharedListUC,
    val deleteListUC: DeleteListUC,
)
