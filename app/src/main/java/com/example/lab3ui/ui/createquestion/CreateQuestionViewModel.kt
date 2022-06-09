package com.example.lab3ui.ui.createquestion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab3ui.data.Repository
import com.example.lab3ui.data.models.Answer
import com.example.lab3ui.data.models.AnswerRequest
import com.example.lab3ui.data.models.QuestionRequest
import com.example.lab3ui.network.PollingApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CreateQuestionViewModel : ViewModel() {
    var questionText: String = ""
    private val _answerList = MutableLiveData<MutableList<AnswerRequest>>()
    val answerList: LiveData<MutableList<AnswerRequest>> = _answerList

    fun addAnswer(answerText: String) {
        if(_answerList.value == null){
            _answerList.value = mutableListOf()
        }
        _answerList.value?.add(AnswerRequest(answerText))
    }

    fun deleteAnswer(position: Int){
        _answerList.value?.removeAt(position)
    }

    fun addQuestion(){
        viewModelScope.launch {
            PollingApi.retrofitService.addQuestion(
                token = "Bearer ${Repository.accessToken}",
                request = QuestionRequest(
                    authorId = Repository.currentUser!!.id,
                    questionText = questionText,
                    answers = _answerList.value!!
                )
            )
        }
    }


}