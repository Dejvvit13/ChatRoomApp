package com.example.chatroomapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chatroomapp.screen.ChatRoomListScreen
import com.example.chatroomapp.screen.ChatScreen
import com.example.chatroomapp.screen.LogInScreen
import com.example.chatroomapp.screen.Screen
import com.example.chatroomapp.screen.SignUpScreen
import com.example.chatroomapp.viewmodel.AuthViewModel

@Composable
fun NavigationGraph(authViewModel: AuthViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.SignupScreen.route) {
            SignUpScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) })
        }
        composable(Screen.LoginScreen.route) {
            LogInScreen(
                authViewModel = authViewModel,
                onNavigateToSignup = { navController.navigate(Screen.SignupScreen.route) },
                onSignInSuccess = { navController.navigate(Screen.ChatRoomsScreen.route) })
        }
        composable(Screen.ChatRoomsScreen.route) {
            ChatRoomListScreen() { navController.navigate("${Screen.ChatScreen.route}/${it.id}") }
        }
        composable("${Screen.ChatScreen.route}/{roomId}") {
            val roomId: String = it.arguments?.getString("roomId") ?: ""
            ChatScreen(roomId = roomId)
        }

    }
}