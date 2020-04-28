package com.example.dokku.api.models

import com.squareup.moshi.Json

data class PhotoDetails(

    @Json(name = "title")
    val title: String = "",

    @Json(name = "photographer")
    val photographer: String = "",

    @Json(name = "created")
    val created: String = "",

    @Json(name = "filename")
    val filename: String = "",

    @Json(name = "id")
    val id: String = ""


)