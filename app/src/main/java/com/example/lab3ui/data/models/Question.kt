package com.example.lab3ui.data.models

import com.squareup.moshi.Json

data class Question(
    val id: Int,
    @Json(name = "question_text") val questionText: String,
    @Json(name = "author_username") val authorUsername: String,
    @Json(name = "author") val authorId: Int,
    @Json(name = "pub_date") val pubDate: String,
    val answers: List<Answer>,
)

data class QuestionRequest(
    @Json(name = "question_text") val questionText: String,
    @Json(name = "author") val authorId: Int,
    val answers: List<AnswerRequest>,
)