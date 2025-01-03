package com.example.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.model.Result
import com.example.chatroomapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository =
        UserRepository(FirebaseAuth.getInstance(), Injection.instance())

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, laseName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.singUp(email, password, firstName, laseName)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }

}