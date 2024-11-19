package com.example.kotlinanimemangaappimport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import com.example.kotlinanimemangaapp.common.Resource
import com.example.kotlinanimemangaapp.domain.interactor.get_list_anime_user.GetListFavoriteAnimeOfUserUC
import com.example.kotlinanimemangaapp.domain.model.ListAnime
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import org.junit.Assert.assertTrue

class GetListFavoriteAnimeOfUserUCTest {

    @Mock
    lateinit var listAnimeRepository: ListAnimeRepository

    lateinit var getListFavoriteAnimeOfUserUC: GetListFavoriteAnimeOfUserUC

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getListFavoriteAnimeOfUserUC = GetListFavoriteAnimeOfUserUC(listAnimeRepository)
    }

    @Test
    fun `test get favorite anime list when repository returns non-empty list`() = runBlockingTest {
        // Given
        val favoriteAnimeList = listOf(
            ListAnime(id_user = "1", id="11", name = "Anime 1"),
            ListAnime(id_user = "2", id = "22", name = "Anime 2"),
            ListAnime(id_user = "3", id = "33", name = "Anime 3")
        )
        `when`(listAnimeRepository.getListFavoriteAnimeOfUser()).thenReturn(favoriteAnimeList)

        // When
        val result: Flow<Resource<List<ListAnime>>> = getListFavoriteAnimeOfUserUC()

        // Then
        val resourceList: List<Resource<List<ListAnime>>> = result.toList()
        assertEquals(2, resourceList.size)

        // First element should be a Loading state
        assertTrue(resourceList[0] is Resource.Loading)

        // Second element should be the Success state with the list of favorite anime
        val resource = resourceList[1]
        assertTrue(resource is Resource.Success)
        assertEquals(favoriteAnimeList, resource.data)
    }


}
