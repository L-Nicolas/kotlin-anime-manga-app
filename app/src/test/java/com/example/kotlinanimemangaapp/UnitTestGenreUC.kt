package com.example.kotlinanimemangaapp

import org.junit.Test

import com.example.kotlinanimemangaapp.data.remote.dto.assets.GenreDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toGenre
import org.junit.Assert.assertEquals



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GenreDtoUnitTest {

    @Test
    fun `toGenre converts GenreDto to Genre correctly`() {
        // Arrange
        val genreDto = GenreDto(
            malId = 1,
            name = "Action",
            type = "genre",
            url = "https://example.com/genre/action"
        )

        // Act
        val genre = genreDto.toGenre()

        // Assert
        assertEquals(1, genre.malId)
        assertEquals("Action", genre.name)
        assertEquals("genre", genre.type)
        assertEquals("https://example.com/genre/action", genre.url)
    }

    @Test
    fun `toGenre converts GenreDto with null values to Genre with default values`() {
        // Arrange
        val genreDto = GenreDto(
            malId = 2,
            name = null,
            type = null,
            url = null
        )

        // Act
        val genre = genreDto.toGenre()

        // Assert
        assertEquals(2, genre.malId)
        assertEquals("", genre.name)
        assertEquals("", genre.type)
        assertEquals("", genre.url)
    }

    @Test
    fun `toGenre converts GenreDto with empty name to Genre with default values`() {
        // Arrange
        val genreDto = GenreDto(
            malId = 3,
            name = "",
            type = "genre",
            url = "https://example.com/genre/empty"
        )

        // Act
        val genre = genreDto.toGenre()

        // Assert
        assertEquals(3, genre.malId)
        assertEquals("", genre.name)
        assertEquals("genre", genre.type)
        assertEquals("https://example.com/genre/empty", genre.url)
    }

}
