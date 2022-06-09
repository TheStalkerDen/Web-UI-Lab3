package com.example.lab3ui.ui.polling

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab3ui.data.Repository
import com.example.lab3ui.data.models.Answer
import com.example.lab3ui.data.models.Question
import com.example.lab3ui.network.PollingApi
import kotlinx.coroutines.launch

private val TAG = "PollingViewModel"

class PollingViewModel : ViewModel() {
    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> = _question
    private val _canVote = MutableLiveData<Boolean>()
    val canVote: LiveData<Boolean> = _canVote
    private val _canDeleteQuestion = MutableLiveData<Boolean>()
    var canDeleteQuestion: LiveData<Boolean>  = _canDeleteQuestion

    var selectedAnswerPosition: Int = -1

    private val _answerList = MutableLiveData<List<Answer>>()
    val answerList: LiveData<List<Answer>> = _answerList

    init {
        viewModelScope.launch {
            _question.value = PollingApi.retrofitService.getQuestion(Repository.currentQuestionId)
            if(Repository.currentUser != null) {
                val response = PollingApi.retrofitService.isAlreadyVote(
                    token = Repository.getAccessTokenForHeader(),
                    questionId = Repository.currentQuestionId
                )
                _canVote.value = !response.isAlreadyVote
            }else{
                _canVote.value = false
            }
            _answerList.value = question.value?.answers
            _canDeleteQuestion.value = calcCanDeleteQuestion()
        }
    }

    private fun calcCanDeleteQuestion():Boolean {
        val currentUser = Repository.currentUser ?: return false
        return currentUser.username == "admin" || question.value!!.authorUsername == currentUser.username
    }

    fun vote(){
        _answerList.value!![selectedAnswerPosition].votes++
        viewModelScope.launch {
            try {
                val response = PollingApi.retrofitService.vote(
                    token = Repository.getAccessTokenForHeader(),
                    questionId = _question.value!!.id,
                    answerId = _answerList.value!![selectedAnswerPosition].id
                )
                Log.d(TAG,response.message)
            }catch (error: Exception){
                Log.d(TAG,error.toString())
            }
        }
    }

    fun deleteQuestion(){
        viewModelScope.launch {
            PollingApi.retrofitService.deleteQuestion(
                token = Repository.getAccessTokenForHeader(),
                questionId = _question.value!!.id
            )
        }
    }

}