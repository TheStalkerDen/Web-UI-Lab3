package com.example.lab3ui.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lab3ui.R
import com.example.lab3ui.data.Repository
import com.example.lab3ui.databinding.FragmentAccountBinding
import com.google.android.material.navigation.NavigationView

val TAG = "AccountFragment"

class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModels()

    private var binding: FragmentAccountBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentAccountBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        binding!!.viewModel = accountViewModel

        binding!!.logoutButton.setOnClickListener{
            logout()
        }
        return fragmentBinding.root
    }

    fun logout(){
        Repository.logout()
        val navView = activity?.findViewById<NavigationView>(R.id.nav_view)!!
        navView.menu.findItem(R.id.nav_login).isVisible = true
        navView.menu.findItem(R.id.nav_register).isVisible = true
        navView.menu.findItem(R.id.nav_account).isVisible = false
        findNavController().navigate(R.id.action_nav_account_to_nav_login)
    }
}