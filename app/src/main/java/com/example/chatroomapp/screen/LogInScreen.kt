package com.example.chatroomapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatroomapp.data.model.Result
import com.example.chatroomapp.viewmodel.AuthViewModel

@Composable
fun LogInScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignup: () -> Unit,
    onSignInSuccess: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val result by authViewModel.authResult.observeAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormField(email, "Email")
            FormField(password, "Password", isPassword = true)
            Button(
                onClick = {
                    authViewModel.login(email.value, password.value)
                    when (result) {
                        is Result.Success -> {
                            onSignInSuccess()
                        }

                        is Result.Error -> {}
                        else -> {}
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            ) { Text(text = "Login") }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Don't have an account? Sign up.",
                modifier = Modifier.clickable { onNavigateToSignup() })
        }
    }
}

