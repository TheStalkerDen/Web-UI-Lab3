package com.example.lab3ui.ui.createquestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3ui.data.models.AnswerRequest
import com.example.lab3ui.databinding.AddNewQuestionAnswerItemBinding

class AnswersAdapter(
    val viewModel: CreateQuestionViewModel
): RecyclerView.Adapter<AnswersAdapter.AnswerViewHolder>(
) {

    private var answerList: List<AnswerRequest> = emptyList()


    class AnswerViewHolder(
        val binding: AddNewQuestionAnswerItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int, answer: AnswerRequest){
            binding.position = position + 1
            binding.answer = answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val binding = AddNewQuestionAnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = answerList[position]
        holder.bind(position, answer)
        holder.binding.deleteAnswer.setOnClickListener {
            deleteAnswer(position)
        }
    }

    fun setAnswersList(answersList: List<AnswerRequest>) {
        this.answerList = answersList
        notifyDataSetChanged();
    }

    fun deleteAnswer(position: Int){
        viewModel.deleteAnswer(position)
        viewModel.answerList.value?.let { setAnswersList(it) }
    }

    override fun getItemCount() = answerList.size
}