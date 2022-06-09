package com.example.lab3ui.ui.polling

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.databinding.FragmentCreateQuestionBinding
import com.example.lab3ui.databinding.FragmentPollingBinding

private val TAG = "PollingFragment"

class PollingFragment : Fragment() {

    private val  pollingViewModel: PollingViewModel by viewModels()

    private var binding: FragmentPollingBinding?  = null
    private lateinit var adapter: AnswersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPollingBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        adapter = AnswersAdapter(pollingViewModel)
        binding!!.answersList.adapter = adapter
        binding!!.fragment = this@PollingFragment
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = pollingViewModel
        }
    }

    fun vote(){
        adapter.wasVoted()
        Toast.makeText(context,"Thank you for your vote!",Toast.LENGTH_SHORT).show()
        binding!!.voteButton.visibility=View.GONE
        pollingViewModel.vote()
    }

    fun deleteQuestion(){
        pollingViewModel.deleteQuestion()
        findNavController().navigate(R.id.action_pollingFragment_to_nav_questions)
    }
}