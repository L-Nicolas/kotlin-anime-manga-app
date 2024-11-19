package com.example.kotlinanimemangaapp.data.remote.dto.assets

import com.example.kotlinanimemangaapp.domain.model.Images
import com.squareup.moshi.Json

data class ImagesDto(
    @Json(name = "jpg")
    val jpgDto: JpgDto,
    @Json(name = "webp")
    val webpDto: WebpDto
)

fun ImagesDto.toImages() : Images = Images(
    jpg = jpgDto.toJpg(),
    webp = webpDto.toWebp()
)