package com

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class FlavorConfig {
    fun addHttpLoggingInterceptor(clientBuilder: OkHttpClient.Builder) {
        val logRequestResponseInterceptor = HttpLoggingInterceptor()
        logRequestResponseInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(logRequestResponseInterceptor)
        clientBuilder.addNetworkInterceptor(StethoInterceptor())
    }

    fun initStetho(application: Application?) {
        Stetho.initializeWithDefaults(application)
    }

    companion object {
        const val BASE_URL = "https://dev-test.dokku.mub.lu/"
    }
}