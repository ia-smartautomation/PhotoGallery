package com.example.dokku.api

import com.FlavorConfig

object ApiConstants {

    const val BASE_URL = FlavorConfig.BASE_URL
    const val API_KEY = "hY53wej6yTe5y3hq4yrht4EewrgtU"

    fun getThumbnailUrl(id: String): String {
        return BASE_URL + "image/thumbnail/" + id
    }

    fun getPhotoUrl(id: String): String {
        return BASE_URL + "image/original/" + id
    }

}