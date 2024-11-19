package com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.delete_list.DeleteAnimeFromListUC
import com.example.kotlinanimemangaapp.domain.model.AnimeListDetail
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeleteAnimeFromListUCTest {

    private lateinit var deleteAnimeFromListUC: DeleteAnimeFromListUC
    private lateinit var listAnimeRepository: ListAnimeRepository

    @Before
    fun setup() {
        listAnimeRepository = object : ListAnimeRepository {
            suspend fun getListAnimeSharedByYourFriend(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeShared(): List<ListAnime> {
                TODO("Not yet implemented")
            }

            override suspend fun getListAnimeOfUser(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeOfUser(idList: String): AnimeListDetail =
                AnimeListDetail(
                    animes = emptyList(),
                    id = idList, // Just passing back the same ID for simplicity
                    id_user = "",
                    shared = false,
                    title = ""
                )
            override suspend fun getListFavoriteAnimeOfUser(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeWatchedOfUser(): List<ListAnime> = emptyList()
            override suspend fun shareList(id: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addList(title: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addAnimeToList(idAnime: Int, idList: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addAnimeToWatched(idAnime: Int): Resource<Unit> = Resource.Success(Unit)

            override suspend fun deleteList(idList: String): Resource<Unit> =
                if (idList.isNotEmpty()) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error(message = "Invalid list ID")
                }

            override suspend fun deleteAnimeFromList(idList: String, idAnime: Int): Resource<Unit> =
                if (idList.isNotEmpty() && idAnime > 0) {
                    Resource.Success(Unit)
                } else {
                    Resource.Error(message = "Invalid parameters")
                }
        }
        deleteAnimeFromListUC = DeleteAnimeFromListUC(listAnimeRepository)
    }

    @Test
    fun `deleteAnimeFromListUC emits Resource Success when deleting anime with valid ID and list ID`() = runBlocking {
        // Arrange
        val listId = "123"
        val animeId = 1

        // Act
        val resourceList = deleteAnimeFromListUC(listId, animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Success
        assertTrue(resourceList[0] is Resource.Loading)
        assertTrue(resourceList[1] is Resource.Success)
    }

    @Test
    fun `deleteAnimeFromListUC emits Resource Error when deleting anime with invalid ID and valid list ID`() = runBlocking {
        // Arrange
        val listId = "123"
        val animeId = -1

        // Act
        val resourceList = deleteAnimeFromListUC(listId, animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Error
        assertTrue(resourceList[0] is Resource.Loading)
        val errorResource = resourceList[1] as Resource.Error
        assertEquals("Invalid parameters", errorResource.message)
    }

    @Test
    fun `deleteAnimeFromListUC emits Resource Error when deleting anime with valid ID and empty list ID`() = runBlocking {
        // Arrange
        val listId = ""
        val animeId = 1

        // Act
        val resourceList = deleteAnimeFromListUC(listId, animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Error
        assertTrue(resourceList[0] is Resource.Loading)
        val errorResource = resourceList[1] as Resource.Error
        assertEquals("Invalid parameters", errorResource.message)
    }

    @Test
    fun `deleteAnimeFromListUC emits Resource Error when API returns an error`() = runBlocking {
        // Arrange
        listAnimeRepository = object : ListAnimeRepository {
            suspend fun getListAnimeSharedByYourFriend(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeShared(): List<ListAnime> {
                TODO("Not yet implemented")
            }

            override suspend fun getListAnimeOfUser(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeOfUser(idList: String): AnimeListDetail =
                AnimeListDetail(
                    animes = emptyList(),
                    id = idList, // Just passing back the same ID for simplicity
                    id_user = "",
                    shared = false,
                    title = ""
                )
            override suspend fun getListFavoriteAnimeOfUser(): List<ListAnime> = emptyList()
            override suspend fun getListAnimeWatchedOfUser(): List<ListAnime> = emptyList()
            override suspend fun shareList(id: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addList(title: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addAnimeToList(idAnime: Int, idList: String): Resource<Unit> = Resource.Success(Unit)
            override suspend fun addAnimeToWatched(idAnime: Int): Resource<Unit> = Resource.Success(Unit)

            override suspend fun deleteList(idList: String): Resource<Unit> =
                Resource.Error(message = "Error with API error")

            override suspend fun deleteAnimeFromList(idList: String, idAnime: Int): Resource<Unit> =
                Resource.Error(message = "Error with API error")
        }
        deleteAnimeFromListUC = DeleteAnimeFromListUC(listAnimeRepository)

        // Act
        val resourceList = deleteAnimeFromListUC("123", 1).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Error
        assertTrue(resourceList[0] is Resource.Loading)
        val errorResource = resourceList[1] as Resource.Error
        assertEquals("Error with API error", errorResource.message)
    }
}
