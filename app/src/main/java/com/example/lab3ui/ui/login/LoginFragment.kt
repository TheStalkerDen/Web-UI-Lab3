package com.example.lab3ui.ui.login

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
import com.example.lab3ui.databinding.FragmentLoginBinding
import com.google.android.material.navigation.NavigationView

val TAG = "LoginFragment"

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
            loginFragment = this@LoginFragment
        }
    }

    fun login() {
        Repository.login(
            binding?.usernameInput?.text.toString(),
            binding?.passwordInput?.text.toString()
        )
        if(Repository.currentUser != null){
            Log.d(TAG,"Login successfully")
            val navView = activity?.findViewById<NavigationView>(R.id.nav_view)
            if (navView != null) {
                navView.menu.findItem(R.id.nav_login).isVisible = false
                navView.menu.findItem(R.id.nav_register).isVisible = false
                navView.menu.findItem(R.id.nav_account).isVisible = true
            }
            findNavController().navigate(R.id.action_nav_login_to_nav_questions)
        }else {
            Toast.makeText(requireContext(),"Can't login", LENGTH_SHORT).show()
        }
    }
}