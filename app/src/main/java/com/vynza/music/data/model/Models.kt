package com.vynza.music.data.model

import com.google.gson.annotations.SerializedName

data class Song(
    val id: Long,
    val title: String,
    @SerializedName("artist_id") val artistId: Long,
    @SerializedName("artist_name") val artistName: String?,
    @SerializedName("album_id") val albumId: Long?,
    @SerializedName("album_title") val albumTitle: String?,
    val duration: Int,
    val genre: String,
    @SerializedName("audio_url") val audioUrl: String,
    @SerializedName("cover_url") val coverUrl: String,
    @SerializedName("play_count") val playCount: Long
)

data class Artist(
    val id: Long,
    val name: String,
    val bio: String,
    @SerializedName("image_url") val imageUrl: String
)

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val role: String,
    @SerializedName("avatar_url") val avatarUrl: String
)

data class AuthResponse(
    val token: String,
    val user: User
)

data class LoginRequest(
    val email: String,
    val password: String
)
