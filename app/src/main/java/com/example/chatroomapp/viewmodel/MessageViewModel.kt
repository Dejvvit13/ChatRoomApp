package com.example.chatroomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatroomapp.data.Injection
import com.example.chatroomapp.data.model.Message
import com.example.chatroomapp.data.model.Result
import com.example.chatroomapp.data.model.User
import com.example.chatroomapp.data.repository.MessageRepository
import com.example.chatroomapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {

    private val messageRepository: MessageRepository = MessageRepository(Injection.instance())
    private val userRepository: UserRepository =
        UserRepository(FirebaseAuth.getInstance(), Injection.instance())

    init {
        loadCurrentUser()
    }

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> get() = _currentUser


    fun setRoomId(roomId: String) {
        _roomId.value = roomId
        loadMessages()
    }


    private fun loadCurrentUser() {
        viewModelScope.launch {
            when (val result = userRepository.getCurrentUser()) {
                is Result.Success -> _currentUser.value = result.data
                is Result.Error -> {}
            }
        }
    }

    fun loadMessages() {
        viewModelScope.launch {
            messageRepository.getChatMessages(_roomId.value.toString())
                .collect { _messages.value = it }
        }
    }

    fun sendMessage(text: String) {
        if (_currentUser.value != null) {
            val message = Message(
                senderFirstName = _currentUser.value!!.firstName,
                senderId = _currentUser.value!!.email,
                text = text
            )
            viewModelScope.launch {
                when (messageRepository.sendMessage(_roomId.value.toString(), message)) {
                    is Result.Success -> Unit
                    is Result.Error -> {}
                }
            }
        }
    }

}