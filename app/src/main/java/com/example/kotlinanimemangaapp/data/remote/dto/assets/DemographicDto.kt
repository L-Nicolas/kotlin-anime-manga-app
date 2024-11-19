package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.domain.model.Demographic
import com.squareup.moshi.Json

data class DemographicDto(
    @Json(name = "mal_id")
    val malId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

fun DemographicDto.toDemographic() : Demographic = Demographic(
    malId = malId,
    name = name,
    type = type,
    url = url
)