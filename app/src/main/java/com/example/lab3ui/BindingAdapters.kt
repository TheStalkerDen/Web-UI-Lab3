package com.example.lab3ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3ui.data.models.Answer
import com.example.lab3ui.data.models.Question
import com.example.lab3ui.ui.polling.AnswersAdapter
import com.example.lab3ui.ui.questions.QuestionsAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Question>?){
    val adapter = recyclerView.adapter as QuestionsAdapter
    if (data != null) {
        adapter.submitList(data.reversed())
    }
}

@BindingAdapter("answerList")
fun bindRecyclerViewToAnswerList(recyclerView: RecyclerView, data: List<Answer>?){
    val adapter = recyclerView.adapter as AnswersAdapter
    if (data != null) {
        adapter.setAnswersList(data)
    }
}