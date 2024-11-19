package com.example.kotlinanimemangaapp.di

import com.example.kotlinanimemangaapp.common.BASE_URL
import com.example.kotlinanimemangaapp.data.remote.api.AnimeApi
import com.example.kotlinanimemangaapp.domain.repository.AnimeDetailRepository
import com.example.kotlinanimemangaapp.domain.repository.ListAnimeRepository
import com.example.kotlinanimemangaapp.domain.repository.AnimeRepository
import com.example.kotlinanimemangaapp.domain.repository.AnimeSavedRepository
import com.example.kotlinanimemangaapp.data.repository.AnimeDatasource
import com.example.kotlinanimemangaapp.data.repository.AnimeDetailDatasource
import com.example.kotlinanimemangaapp.data.repository.AnimeSavedDatasource
import com.example.kotlinanimemangaapp.data.repository.ListAnimeDatasource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val okHttpClient = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApi = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(AnimeApi::class.java)

    @Provides
    @Singleton
    fun provideAnimeRepository(animeApi: AnimeApi): AnimeRepository =
        AnimeDatasource(animeApi)

    @Provides
    @Singleton
    fun provideAnimeSavedRepository(database: FirebaseFirestore): AnimeSavedRepository = AnimeSavedDatasource(database)

    @Provides
    @Singleton
    fun provideAnimeListRepository(animeDatasource: AnimeDatasource, database: FirebaseFirestore): ListAnimeRepository = ListAnimeDatasource(animeDatasource, database)

    @Provides
    @Singleton
    fun provideAnimeDetailRepository(animeDatasource: AnimeDatasource, database: FirebaseFirestore): AnimeDetailRepository = AnimeDetailDatasource(animeDatasource,database)


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

}