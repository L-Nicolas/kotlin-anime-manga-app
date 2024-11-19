package com.example.kotlinanimemangaapp
import com.example.kotlinanimemangaapp.data.remote.dto.assets.AiredDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.FromDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.PropDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.ToDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toAired
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toFrom
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toTo
import org.junit.Assert.assertEquals
import org.junit.Test

class AiredDtoUnitTest {

    @Test
    fun `toAired converts AiredDto to Aired correctly`() {
        // Arrange
        val fromDto = FromDto(day = 1, month = 1, year = 2022)
        val toDto = ToDto(day = 31, month = 12, year = 2022)
        val propDto = PropDto(from = fromDto, string = "Some Prop Value", to = toDto)
        val airedDto = AiredDto(from = "2022-01-01", prop = propDto, to = "2022-12-31")

        // Act
        val aired = airedDto.toAired()

        // Assert
        assertEquals("2022-01-01", aired.from)
        assertEquals(fromDto.toFrom(), aired.prop.from)
        assertEquals("Some Prop Value", aired.prop.string)
        assertEquals(toDto.toTo(), aired.prop.to)
        assertEquals("2022-12-31", aired.to)
    }

    @Test
    fun `toAired converts AiredDto with null values to Aired with default values`() {
        // Arrange
        val fromDto = FromDto(day = null, month = null, year = null)
        val toDto = ToDto(day = null, month = null, year = null)
        val propDto = PropDto(from = fromDto, string = null, to = toDto)
        val airedDto = AiredDto(from = null, prop = propDto, to = null)

        // Act
        val aired = airedDto.toAired()

        // Assert
        assertEquals("", aired.from)
        assertEquals(fromDto.toFrom(), aired.prop.from)
        assertEquals("", aired.prop.string)
        assertEquals(toDto.toTo(), aired.prop.to)
        assertEquals("", aired.to)
    }
}
