package com.example.kotlinanimemangaapp.data.remote
/*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.kotlinanimemangaapp.data.local.AnimeDatabase
import com.example.kotlinanimemangaapp.data.local.AnimeEntity
import com.example.kotlinanimemangaapp.data.remote.api.AnimeApi
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toAnimeEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val animeDb: AnimeDatabase,
    private val animeApi: AnimeApi
): RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.malId / state.config.pageSize) + 1
                    }
                }
            }

            val apiResponse = animeApi.getAnime(page = page)
            val animes = apiResponse.data
            val endOfPaginationReached = animes.isEmpty()
            animeDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    animeDb.dao().clearAll()
                }
                val animeEntities = animes.map { it.toAnimeEntity() }
                animeDb.dao().upsertAll(animeEntities)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
        //return MediatorResult.Success(endOfPaginationReached = true)
    }
}*/