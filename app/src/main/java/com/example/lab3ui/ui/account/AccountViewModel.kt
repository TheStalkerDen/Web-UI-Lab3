package com.example.lab3ui.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab3ui.data.Repository
import com.example.lab3ui.data.models.User

class AccountViewModel : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init{
        _user.value = Repository.currentUser
    }
}
