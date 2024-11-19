package com.example.kotlinanimemangaapp.data.remote.dto.assets


import com.example.kotlinanimemangaapp.domain.model.Anime
import com.squareup.moshi.Json

data class AnimeDto(
    @Json(name = "aired")
    val airedDto: AiredDto,
    @Json(name = "airing")
    val airing: Boolean,
    @Json(name = "approved")
    val approved: Boolean,
    @Json(name = "background")
    val background: String?,
    @Json(name = "broadcast")
    val broadcast: BroadcastDto,
    @Json(name = "demographics")
    val demographics: List<DemographicDto>,
    @Json(name = "duration")
    val duration: String?,
    @Json(name = "episodes")
    val episodes: Int?,
    @Json(name = "explicit_genres")
    val explicitGenre: List<ExplicitGenreDto>,
    @Json(name = "favorites")
    val favorites: Int?,
    @Json(name = "genres")
    val genres: List<GenreDto>,
    @Json(name = "images")
    val images: ImagesDto,
    @Json(name = "licensors")
    val licensors: List<LicensorDto>,
    @Json(name = "mal_id")
    val malId: Int,
    @Json(name = "members")
    val members: Int?,
    @Json(name = "popularity")
    val popularity: Int?,
    @Json(name = "producers")
    val producers: List<ProducerDto>,
    @Json(name = "rank")
    val rank: Int?,
    @Json(name = "rating")
    val rating: String?,
    @Json(name = "score")
    val score: Float?,
    @Json(name = "scored_by")
    val scoredBy: Int?,
    @Json(name = "season")
    val season: String?,
    @Json(name = "source")
    val source: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "studios")
    val studios: List<StudioDto>,
    @Json(name = "synopsis")
    val synopsis: String?,
    @Json(name = "themes")
    val themes: List<ThemeDto>,
    @Json(name = "title")
    val title: String,
    @Json(name = "title_english")
    val titleEnglish: String?,
    @Json(name = "title_japanese")
    val titleJapanese: String?,
    @Json(name = "title_synonyms")
    val titleSynonyms: List<String>,
    @Json(name = "titles")
    val titles: List<TitleDto>,
    @Json(name = "trailer")
    val trailer: TrailerDto,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "year")
    val year: Int?
)

fun AnimeDto.toAnime() : Anime = Anime(
    aired = airedDto.toAired(),
    airing = airing,
    approved = approved,
    background = background?: "",
    broadcast = broadcast.toBroadcast(),
    demographics = demographics.map { it.toDemographic() },
    duration = duration?: "",
    episodes = episodes,
    explicitGenres = explicitGenre.map { it.toExplicitGenre() },
    favorites = favorites,
    genres = genres.map { it.toGenre() },
    images = images.toImages(),
    licensors = licensors.map { it.toLicensor() },
    malId = malId,
    members = members,
    popularity = popularity,
    producers = producers.map { it.toProducer() },
    rank = rank,
    rating = rating?: "",
    score = score,
    scoredBy = scoredBy,
    season = season?: "",
    source = source?: "",
    status = status?: "",
    studios = studios.map { it.toStudio() },
    synopsis = synopsis?: "",
    themes = themes.map { it.toTheme() },
    title = title,
    titleEnglish = titleEnglish?:"",
    titleJapanese = titleJapanese?:"",
    titleSynonyms = titleSynonyms,
    titles = titles.map { it.toTitle() },
    trailer = trailer.toTrailer(),
    type = type?: "",
    url = url?: "",
    year = year
)
