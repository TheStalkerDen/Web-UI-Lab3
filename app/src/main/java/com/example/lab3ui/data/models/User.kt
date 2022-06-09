package com.example.lab3ui.data.models

import com.squareup.moshi.Json

data class User(
    val id: Int,
    val username: String,
    @Json(name= "first_name") val firstName: String,
    @Json(name= "last_name") val lastName: String,
    val email: String
)