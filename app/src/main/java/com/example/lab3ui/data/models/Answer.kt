package com.example.lab3ui.data.models

import com.squareup.moshi.Json

data class Answer(
    val id: Int,
    @Json(name = "answer_text") val answerText: String,
    var votes: Int
)

data class AnswerRequest(
    @Json(name = "answer_text") val answerText: String
)