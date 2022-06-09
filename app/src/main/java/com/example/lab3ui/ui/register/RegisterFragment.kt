package com.example.lab3ui.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.databinding.FragmentLoginBinding
import com.example.lab3ui.databinding.FragmentRegisterBinding
import com.example.lab3ui.network.RegisterRequest

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        binding.lifecycleOwner = this;
        binding.registerFragment = this@RegisterFragment
        return binding.root
    }

    fun register() {
        Repository.register(
            RegisterRequest(
                username= binding.usernameInput.text.toString(),
                lastName= binding.lastNameInput.text.toString(),
                firstName= binding.firstNameInput.text.toString(),
                email=binding.emailInput.text.toString(),
                password= binding.passwordInput.text.toString()
            )
        )
        findNavController().navigate(R.id.nav_login)
    }
}