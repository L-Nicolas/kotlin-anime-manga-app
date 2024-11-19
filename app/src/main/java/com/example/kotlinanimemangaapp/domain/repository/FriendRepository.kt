package com.example.kotlinanimemangaapp.domain.repository

import com.example.kotlinanimemangaapp.domain.model.Friend
import com.example.kotlinanimemangaapp.domain.model.ListAnime

interface FriendRepository {
    suspend fun getFriends(): List<Friend>
}