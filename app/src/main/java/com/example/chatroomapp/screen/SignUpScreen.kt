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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatroomapp.viewmodel.AuthViewModel


@Composable
fun SignUpScreen(authViewModel: AuthViewModel, onNavigateToLogin: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }

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
            FormField(firstName, "First Name")
            FormField(lastName, "Last Name")
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp), onClick = {
                authViewModel.signUp(
                    email.value,
                    password.value,
                    firstName.value,
                    lastName.value
                )
                email.value = ""
                password.value = ""
                firstName.value = ""
                lastName.value = ""
            }) { Text(text = "Sign Up") }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Already have an account? Sign in.",
                modifier = Modifier.clickable { onNavigateToLogin() })
        }
    }
}