package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Producer
import com.squareup.moshi.Json

data class ProducerDto(
    @Json(name = "mal_id")
    val malId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)

fun ProducerDto.toProducer() : Producer = Producer(
    malId = malId,
    name = name,
    type = type,
    url = url
)