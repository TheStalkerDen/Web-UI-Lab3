package com.example.lab3ui.ui.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab3ui.data.models.Question
import com.example.lab3ui.network.PollingApi
import kotlinx.coroutines.launch

class QuestionsViewModel: ViewModel() {
    private val _questionList = MutableLiveData<List<Question>>()
    val questionList: LiveData<List<Question>> = _questionList

    init{
        getQuestions()
    }

    private fun getQuestions(){
        viewModelScope.launch {
            _questionList.value = PollingApi.retrofitService.getQuestions()
        }
    }

    fun updateQuestionList(){
        getQuestions()
    }
}