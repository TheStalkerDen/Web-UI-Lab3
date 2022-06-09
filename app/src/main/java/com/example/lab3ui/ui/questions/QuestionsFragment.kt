package com.example.lab3ui.ui.questions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.databinding.FragmentQuestionsBinding

val TAG = "QuestionsFragment"

class QuestionsFragment: Fragment() {
    private val  questionsViewModel: QuestionsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =  FragmentQuestionsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = questionsViewModel
        binding.questionsCard.adapter = QuestionsAdapter()
        binding.questionFragment = this@QuestionsFragment
        binding.addQuestionButton.visibility = if(Repository.currentUser == null) View.GONE else View.VISIBLE;
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        questionsViewModel.updateQuestionList()
    }

    fun goToCreteQuestionFragment(){
        findNavController().navigate(R.id.action_nav_questions_to_createQuestionFragment)
    }

}