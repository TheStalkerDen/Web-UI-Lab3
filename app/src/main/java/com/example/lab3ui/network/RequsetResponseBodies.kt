package com.example.lab3ui.network

import com.squareup.moshi.Json

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val access: String,
    val refresh: String
)

data class RegisterRequest(
    val username: String,
    @Json(name="first_name") val firstName: String,
    @Json(name="last_name") val lastName: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val username: String,
    @Json(name="first_name") val firstName: String,
    @Json(name="last_name") val lastName: String,
    val email: String,
)

data class VoteResponse(
    val message: String
)

data class IsAlreadyVoteResponse(
    @Json(name="is-already-vote") val isAlreadyVote: Boolean
)