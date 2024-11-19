package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Broadcast
import com.squareup.moshi.Json

data class BroadcastDto(
    @Json(name = "day")
    val day: String?,
    @Json(name = "string")
    val string: String?,
    @Json(name = "time")
    val time: String?,
    @Json(name = "timezone")
    val timezone: String?
)

fun BroadcastDto.toBroadcast(): Broadcast = Broadcast(
    day = day?: "",
    string = string?: "",
    time = time?: "",
    timezone = timezone?: ""
)