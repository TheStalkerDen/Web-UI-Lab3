package com.example.lab3ui.ui.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.data.models.Question
import com.example.lab3ui.databinding.QuestionItemBinding

class QuestionsAdapter:
    ListAdapter<Question, QuestionsAdapter.QuestionViewHolder>(DiffCallback) {

    private lateinit var binding: QuestionItemBinding

    class QuestionViewHolder (
        private val binding: QuestionItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(question: Question) {
            binding.question = question
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.questionText == newItem.questionText
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        binding = QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = getItem(position)
        holder.bind(question)
        holder.itemView.setOnClickListener {
            Repository.currentQuestionId = question.id
            it.findNavController().navigate(R.id.action_nav_questions_to_pollingFragment)
        }
    }

}