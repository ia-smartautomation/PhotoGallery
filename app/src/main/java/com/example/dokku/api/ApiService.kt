package com.example.dokku.api

import com.example.dokku.api.models.PhotoDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/photo")
    suspend fun getPhotos(@Query("page") page: Int): Response<List<PhotoDetails>>

    @GET("api/photo/{id}")
    suspend fun getPhotoDetails(@Path("id") id: String): Response<PhotoDetails>
}