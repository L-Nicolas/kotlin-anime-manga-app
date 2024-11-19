package com.example.kotlinanimemangaapp.data.remote.api

import com.example.kotlinanimemangaapp.data.remote.dto.assets.AnimeDetailResponse
import com.example.kotlinanimemangaapp.data.remote.dto.assets.AnimeResponse
import com.example.kotlinanimemangaapp.data.remote.dto.assets.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {
    @GET("/v4/anime")
    suspend fun getAnime(
        @Query("order_by") orderBy: String? = "popularity",
        @Query("q") searchText: String? = null,
        @Query("page") page: Int? = 1,
        @Query("limit") limit: Int? = 25,
        @Query("genres") listIdGenre: List<Int>? = null
    ): AnimeResponse

    @GET("/v4/anime/{idAnime}")
    suspend fun getAnime(@Path("idAnime") idAnime: Int): AnimeDetailResponse

    @GET("/v4/genres/anime")
    suspend fun getGenres(): GenreResponse
}