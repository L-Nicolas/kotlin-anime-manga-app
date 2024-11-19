package com.example.kotlinanimemangaapp

import com.example.kotlinanimemangaapp.data.remote.dto.assets.BroadcastDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.toBroadcast
import org.junit.Assert.assertEquals
import org.junit.Test

class BroadcastDtoUnitTest {

    @Test
    fun `toBroadcast converts BroadcastDto to Broadcast correctly`() {
        // Arrange
        val broadcastDto = BroadcastDto(
            day = "Saturdays",
            string = "Some Broadcast String",
            time = "23:30",
            timezone = "GMT"
        )

        // Act
        val broadcast = broadcastDto.toBroadcast()

        // Assert
        assertEquals("Saturdays", broadcast.day)
        assertEquals("Some Broadcast String", broadcast.string)
        assertEquals("23:30", broadcast.time)
        assertEquals("GMT", broadcast.timezone)
    }

    @Test
    fun `toBroadcast converts BroadcastDto with null values to Broadcast with default values`() {
        // Arrange
        val broadcastDto = BroadcastDto(
            day = null,
            string = null,
            time = null,
            timezone = null
        )

        // Act
        val broadcast = broadcastDto.toBroadcast()

        // Assert
        assertEquals("", broadcast.day)
        assertEquals("", broadcast.string)
        assertEquals("", broadcast.time)
        assertEquals("", broadcast.timezone)
    }
}
