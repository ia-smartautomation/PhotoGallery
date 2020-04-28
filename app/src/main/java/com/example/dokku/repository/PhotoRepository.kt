package com.example.dokku.repository

import android.app.Application
import com.example.dokku.api.ApiClient
import com.example.dokku.api.models.PhotoDetails
import com.example.dokku.utils.Result


class PhotoRepository(private val application: Application) : BaseRepository(application) {

    suspend fun getPhotos(page: Int): Result<List<PhotoDetails>> {
        return getResult { ApiClient.getClient().getPhotos(page) }
    }

    suspend fun getPhotoDetails(id: String): Result<PhotoDetails> {
        return getResult { ApiClient.getClient().getPhotoDetails(id) }
    }


}