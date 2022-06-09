package com.example.lab3ui.data

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.lab3ui.data.models.User
import com.example.lab3ui.network.LoginRequest
import com.example.lab3ui.network.LoginResponse
import com.example.lab3ui.network.PollingApi
import com.example.lab3ui.network.RegisterRequest
import kotlinx.coroutines.*

val TAG = "Repository"

object Repository {
    var accessToken: String = ""
    var refreshToken: String = ""
    var currentUser: User? = null

    var currentQuestionId: Int = 0

    fun getAccessTokenForHeader(): String{
        return "Bearer $accessToken"
    }

    fun login(username: String, password: String) {
        runBlocking {
            launch {
                try {
                    val response: LoginResponse = PollingApi.retrofitService.login(
                        LoginRequest(
                            username = username,
                            password = password
                        )
                    )
                    accessToken = response.access
                    refreshToken = response.refresh
                    Log.d(TAG, "Access token = $accessToken")
                    Log.d(TAG, "Refresh token = $refreshToken")

                    val user = PollingApi.retrofitService.getUser(
                        token = "Bearer $accessToken",
                        user = username
                    )
                    currentUser = user

                    Log.d(TAG, "username = ${user.username}")

                } catch (err: Exception) {
                    Log.d(TAG, "can't login")
                }
            }
        }
    }

    fun register(request: RegisterRequest){
        runBlocking {
            launch {
                PollingApi.retrofitService.register(request)
            }
        }
    }

    fun logout(){
        accessToken = ""
        refreshToken = ""
        currentUser = null
    }
}
