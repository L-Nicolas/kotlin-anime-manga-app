package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Aired
import com.squareup.moshi.Json

data class AiredDto(
    @Json(name = "from")
    val from: String?,
    @Json(name = "prop")
    val prop: PropDto,
    @Json(name = "to")
    val to: String?
)

fun AiredDto.toAired() : Aired = Aired(
        from = from?: "",
        prop = prop.toProp(),
        to = to?: ""
)


