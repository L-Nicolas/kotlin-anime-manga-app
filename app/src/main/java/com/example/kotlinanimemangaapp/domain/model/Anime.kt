package com.example.kotlinanimemangaapp.domain.model

import com.example.kotlinanimemangaapp.data.remote.dto.assets.ExplicitGenreDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.ImagesDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.LicensorDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.ProducerDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.StudioDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.ThemeDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.TitleDto
import com.example.kotlinanimemangaapp.data.remote.dto.assets.TrailerDto
import com.example.kotlinanimemangaapp.domain.model.Aired
import com.example.kotlinanimemangaapp.domain.model.Broadcast
import com.example.kotlinanimemangaapp.domain.model.Demographic
import com.example.kotlinanimemangaapp.domain.model.ExplicitGenre
import com.example.kotlinanimemangaapp.domain.model.Images
import com.example.kotlinanimemangaapp.domain.model.Licensor
import com.example.kotlinanimemangaapp.domain.model.Producer
import com.example.kotlinanimemangaapp.domain.model.Studio
import com.example.kotlinanimemangaapp.domain.model.Theme
import com.example.kotlinanimemangaapp.domain.model.Title
import com.example.kotlinanimemangaapp.domain.model.Trailer

data class Anime(
    val aired: Aired,
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val broadcast: Broadcast,
    val demographics: List<Demographic>,
    val duration: String,
    val episodes: Int?,
    val explicitGenres: List<ExplicitGenre>,
    val favorites: Int?,
    val genres: List<Genre>,
    val images: Images,
    val licensors: List<Licensor>,
    val malId: Int,
    val members: Int?,
    val popularity: Int?,
    val producers: List<Producer>,
    val rank: Int?,
    val rating: String,
    val score: Float?,
    val scoredBy: Int?,
    val season: String,
    val source: String,
    val status: String,
    val studios: List<Studio>,
    val synopsis: String,
    val themes: List<Theme>,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
    val titleSynonyms: List<String>,
    val titles: List<Title>,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int?
)

