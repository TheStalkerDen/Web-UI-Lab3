package com.example.lab3ui.ui.createquestion

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.databinding.FragmentCreateQuestionBinding
import com.example.lab3ui.databinding.FragmentLoginBinding
import com.google.android.material.navigation.NavigationView

private val TAG = "CreateQuestionFragment"

class CreateQuestionFragment : Fragment() {

    private val createQuestionViewModel: CreateQuestionViewModel by viewModels()

    private var binding: FragmentCreateQuestionBinding?  = null
    private  lateinit var adapter: AnswersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCreateQuestionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        adapter = AnswersAdapter(createQuestionViewModel)
        binding!!.recyclerView.adapter = adapter
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = createQuestionViewModel
            fragment = this@CreateQuestionFragment
        }
    }

    fun addAnswer(){
        createQuestionViewModel.addAnswer(
            binding?.answerEditText?.text.toString()
        )
        createQuestionViewModel.answerList.value?.let {
            adapter.setAnswersList(it) }
    }

    fun addQuestion(){
        createQuestionViewModel.questionText = binding?.questionEditText?.text.toString()
        createQuestionViewModel.addQuestion()
        findNavController().navigate(R.id.action_createQuestionFragment_to_nav_questions)
    }
}