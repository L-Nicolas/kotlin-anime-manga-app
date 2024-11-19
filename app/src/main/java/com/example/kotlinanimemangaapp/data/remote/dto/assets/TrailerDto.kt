package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.domain.model.Trailer
import com.squareup.moshi.Json

data class TrailerDto(
    @Json(name = "embed_url")
    val embedUrl: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "youtube_id")
    val youtubeId: String?
)

fun TrailerDto.toTrailer(): Trailer = Trailer(
    embedUrl = embedUrl,
    url = url,
    youtubeId = youtubeId
)