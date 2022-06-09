package com.example.lab3ui.ui.polling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3ui.data.models.Answer
import com.example.lab3ui.data.models.AnswerRequest
import com.example.lab3ui.databinding.PollingAnswerItemBinding

class AnswersAdapter(
    val viewModel: PollingViewModel
) : RecyclerView.Adapter<AnswersAdapter.AnswerViewHolder>() {

    private var answerList: List<Answer> = emptyList()

    private var isNewRadioButtonChecked = false
    private var lastCheckedPosition = -1
    private var wasVoted = false

    class AnswerViewHolder(
        val binding: PollingAnswerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(answer: Answer) {
            binding.answer = answer
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnswersAdapter.AnswerViewHolder {
        val binding =
            PollingAnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswersAdapter.AnswerViewHolder, position: Int) {
        val answser = answerList[position]
        holder.bind(answser)

        if (viewModel.canVote.value == false || wasVoted) {
            holder.binding.chooseAnswerRadioButton.visibility= View.GONE
            holder.binding.pollingAnswerTextView.visibility = View.VISIBLE
        } else {
            if (isNewRadioButtonChecked) {
                holder.binding.chooseAnswerRadioButton.isChecked = lastCheckedPosition == position
            }

            holder.binding.chooseAnswerRadioButton.setOnClickListener {
                checkButton(position)
            }
        }
    }

    private fun checkButton(position: Int) {
        isNewRadioButtonChecked = true
        lastCheckedPosition = position
        viewModel.selectedAnswerPosition = position
        notifyDataSetChanged()
    }

    fun setAnswersList(answersList: List<Answer>) {
        this.answerList = answersList
        notifyDataSetChanged();
    }

    fun wasVoted() {
        wasVoted = true
        notifyDataSetChanged()
    }

    override fun getItemCount() = answerList.size

}