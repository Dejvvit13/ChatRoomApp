package com.example.chatroomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.chatroomapp.navigation.NavigationGraph
import com.example.chatroomapp.ui.theme.ChatRoomAppTheme
import com.example.chatroomapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = viewModel()
            ChatRoomAppTheme {
                NavigationGraph(navController = navController, authViewModel = authViewModel)
            }
        }
    }
}
