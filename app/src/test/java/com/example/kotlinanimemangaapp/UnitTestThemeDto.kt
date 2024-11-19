package com.example.kotlinanimemangaapp

import com.example.kotlinanimemangaapp.data.remote.dto.assets.ThemeDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toTheme
import org.junit.Assert.assertEquals
import org.junit.Test

class ThemeDtoUnitTest {

    @Test
    fun `toTheme converts ThemeDto to Theme correctly`() {
        // Arrange
        val themeDto = ThemeDto(
            malId = 1,
            name = "Opening 1",
            type = "Opening",
            url = "https://example.com/themes/opening1"
        )

        // Act
        val theme = themeDto.toTheme()

        // Assert
        assertEquals(1, theme.malId)
        assertEquals("Opening 1", theme.name)
        assertEquals("Opening", theme.type)
        assertEquals("https://example.com/themes/opening1", theme.url)
    }

}
