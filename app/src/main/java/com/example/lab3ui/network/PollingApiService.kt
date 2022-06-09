package com.example.lab3ui.network

import com.example.lab3ui.data.Repository
import com.example.lab3ui.data.models.Question
import com.example.lab3ui.data.models.QuestionRequest
import com.example.lab3ui.data.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL =
    "http://192.168.0.178:8000/api/v1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PollingApiService {
    @GET("questions")
    suspend fun getQuestions(): List<Question>

    @GET("questions/{questionId}")
    suspend fun getQuestion(@Path("questionId") questionId: Int): Question

    @GET("questions/{questionId}/is-already-vote")
    suspend fun isAlreadyVote(
        @Header("Authorization") token: String,
        @Path("questionId") questionId: Int
    ): IsAlreadyVoteResponse

    @DELETE("questions/{questionId}")
    suspend fun deleteQuestion(
        @Header("Authorization") token: String,
        @Path("questionId") questionId: Int,
    ): Response<Unit>


    @PATCH("questions/{questionId}/answers/{answerId}/vote")
    suspend fun vote(
        @Header("Authorization") token: String,
        @Path("questionId") questionId: Int,
        @Path("answerId") answerId: Int
    ): VoteResponse

    @POST("questions")
    suspend fun addQuestion(
        @Header("Authorization") token: String,
        @Body request: QuestionRequest
    ): Question

    @GET("users/{user}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("user") user: String
    ): User

    @POST("token")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("users")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}

object PollingApi {
    val retrofitService: PollingApiService by lazy {
        retrofit.create(PollingApiService::class.java)
    }
}