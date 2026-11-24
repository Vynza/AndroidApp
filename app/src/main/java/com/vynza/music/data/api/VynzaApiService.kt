package com.vynza.music.data.api

import com.vynza.music.data.model.AuthResponse
import com.vynza.music.data.model.LoginRequest
import com.vynza.music.data.model.Song
import com.vynza.music.data.model.Artist
import retrofit2.Response
import retrofit2.http.*

interface VynzaApiService {

    @POST("auth/login")
    suspend fun login(@Body req: LoginRequest): Response<AuthResponse>

    @GET("songs")
    suspend fun getSongs(
        @Query("search") search: String? = null,
        @Query("genre") genre: String? = null
    ): Response<List<Song>>

    @GET("artists")
    suspend fun getArtists(): Response<List<Artist>>

    @GET("favorites")
    suspend fun getFavorites(@Header("Authorization") token: String): Response<List<Song>>

    @POST("favorites/{songId}")
    suspend fun addFavorite(
        @Header("Authorization") token: String,
        @Path("songId") songId: Long
    ): Response<Map<String, String>>

    @DELETE("favorites/{songId}")
    suspend fun removeFavorite(
        @Header("Authorization") token: String,
        @Path("songId") songId: Long
    ): Response<Map<String, String>>
}
