package com

import android.app.Application
import okhttp3.OkHttpClient

class FlavorConfig {
    fun addHttpLoggingInterceptor(clientBuilder: OkHttpClient.Builder?) {
    }

    fun initStetho(application: Application?) {
    }

    companion object {
        const val BASE_URL = "https://dev-test.dokku.mub.lu/"
    }
}