package com.example.chatroomapp.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormField(value: MutableState<String>, label: String, isPassword: Boolean = false) {
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        visualTransformation = (if (isPassword) PasswordVisualTransformation() else VisualTransformation.None)
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun FormFieldPreview() {
    var value = mutableStateOf("Test Field")
    FormField(value, "Test Field")
}