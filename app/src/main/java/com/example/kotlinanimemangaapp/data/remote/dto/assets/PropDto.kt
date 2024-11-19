package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Prop
import com.squareup.moshi.Json

data class PropDto(
    @Json(name = "from")
    val from: FromDto,
    @Json(name = "string")
    val string: String?,
    @Json(name = "to")
    val to: ToDto
)

fun PropDto.toProp(): Prop = Prop(
    from = from.toFrom(),
    string = string?: "",
    to = to.toTo()
)