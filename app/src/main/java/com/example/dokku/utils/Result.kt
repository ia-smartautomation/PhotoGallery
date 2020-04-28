package com.example.dokku.utils

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Unsuccess<out T>(val code: Int, val jsonString: String?) : Result<T>()
    data class Error<out T>(val message: String) : Result<T>()
}