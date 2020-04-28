package com.example.dokku.repository

import android.app.Application
import com.example.dokku.utils.Result

import retrofit2.Response

open class BaseRepository(private val application: Application) {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    return Result.Success(body)
            } else {
                return Result.Unsuccess(response.code(), response.errorBody()?.string())
            }
            val message = " ${response.code()} ${response.message()}"

            return Result.Error("Network call has failed for a following reason: $message")

        } catch (e: Exception) {
            val message = (e.message ?: e.toString())
            return Result.Error("Network call has failed for a following reason: $message")
        }
    }

}