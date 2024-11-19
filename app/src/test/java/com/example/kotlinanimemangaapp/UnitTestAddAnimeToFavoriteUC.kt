package com.example.kotlinanimemangaapp


import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.add_list.AddAnimeToFavoriteUC
import com.example.kotlinanimemangaapp.domain.repository.AnimeSavedRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddAnimeToFavoriteUCTest {

    private lateinit var addAnimeToFavoriteUC: AddAnimeToFavoriteUC
    private lateinit var animeSavedRepository: AnimeSavedRepository

    @Before
    fun setup() {
        animeSavedRepository = object : AnimeSavedRepository {
            override suspend fun addAnimeToFavorite(id: Int): Resource<Unit> {
                return Resource.Success(Unit)
            }
        }
        addAnimeToFavoriteUC = AddAnimeToFavoriteUC(animeSavedRepository)
    }

    @Test
    fun `addAnimeToFavoriteUC emits Resource Success when adding anime to favorite`() = runBlocking {
        // Arrange
        val animeId = 1

        // Act
        val resourceList = addAnimeToFavoriteUC(animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Success
        assertTrue(resourceList[0] is Resource.Loading)
        assertTrue(resourceList[1] is Resource.Success)
    }

    @Test
    fun `addAnimeToFavoriteUC emits Resource Error when there is an error`() = runBlocking {
        // Arrange
        val animeId = 1
        val errorMessage = "Some error message"
        animeSavedRepository = object : AnimeSavedRepository {
            override suspend fun addAnimeToFavorite(id: Int): Resource<Unit> {
                return Resource.Error<Unit>(message = errorMessage)
            }
        }
        addAnimeToFavoriteUC = AddAnimeToFavoriteUC(animeSavedRepository)

        // Act
        val resourceList = addAnimeToFavoriteUC(animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Error
        assertTrue(resourceList[0] is Resource.Loading)
        assertTrue(resourceList[1] is Resource.Error)
        assertEquals(errorMessage, (resourceList[1] as Resource.Error).message)
    }

    @Test
    fun `addAnimeToFavoriteUC emits Resource Success when adding anime to favorite with different repository setup`() = runBlocking {
        // Arrange
        val animeId = 2
        animeSavedRepository = object : AnimeSavedRepository {
            override suspend fun addAnimeToFavorite(id: Int): Resource<Unit> {
                return Resource.Success(Unit)
            }
        }
        addAnimeToFavoriteUC = AddAnimeToFavoriteUC(animeSavedRepository)

        // Act
        val resourceList = addAnimeToFavoriteUC(animeId).toList()

        // Assert
        assertEquals(2, resourceList.size) // Two emissions: Loading and Success
        assertTrue(resourceList[0] is Resource.Loading)
        assertTrue(resourceList[1] is Resource.Success)
    }
}
