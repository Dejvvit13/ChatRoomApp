package com.example.chatroomapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatroomapp.data.model.Room
import com.example.chatroomapp.viewmodel.RoomViewModel

@Composable
fun ChatRoomListScreen(roomViewModel: RoomViewModel = viewModel(), onJoinClicked: (Room) -> Unit) {
    val rooms by roomViewModel.rooms.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Chat rooms", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Button(
                    colors = ButtonColors(
                        containerColor = Color.Magenta,
                        contentColor = Color.Black,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color.Gray
                    ),
                    onClick = { showDialog = true },

                    ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                    )
                }
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(rooms) { room ->
                    RoomItem(
                        room = room,
                        onJoinClicked = { onJoinClicked(room) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = true },
                    title = { Text(text = "Create a new room") },
                    text = {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    },
                    confirmButton = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                showDialog = false
                                name = ""
                            }) { Text(text = "Cancel") }
                            Button(onClick = {
                                if (name.isNotBlank()) {
                                    showDialog = false
                                    roomViewModel.createRoom(name)
                                    name = ""
                                }
                            }) { Text(text = "Add") }
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun RoomItem(room: Room, onJoinClicked: (Room) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = room.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(onClick = { onJoinClicked(room) }) { Text(text = "Join") }
    }
}