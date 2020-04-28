package com.example.dokku.api

import android.app.Application
import com.FlavorConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private lateinit var webService: ApiService

        fun getClient(): ApiService {

            val flavorConfig = FlavorConfig()

            if (!Companion::webService.isInitialized) {

                val clientBuilder = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val requestBuilder = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Accept", "application/json")
                            .addHeader("x-api-key", ApiConstants.API_KEY)


                        chain.proceed(requestBuilder.build())
                    }


                flavorConfig.addHttpLoggingInterceptor(clientBuilder)

                val client = clientBuilder.build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build()


                webService = retrofit.create(ApiService::class.java)

                return webService
            }
            return webService
        }
    }
}