package com.example.appdevelopment.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.appdevelopment.ui.screens.loginView.LoginEvent

@Composable
fun DefaultFieldBox(
    currentValue: String,
    onEvent: (String) -> Unit,
    focusedColor: Color,
    unfocusedColor: Color,
    label: String,
    password: Boolean
){

    var passwordVisible by remember { mutableStateOf(password) }

    OutlinedTextField(
        value = currentValue,
        onValueChange = {
            onEvent(it)
                        },
        label = { Text(label) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedColor,
            unfocusedBorderColor = unfocusedColor,
            focusedLabelColor = focusedColor,
            unfocusedLabelColor = unfocusedColor
        ),
        visualTransformation = if (!passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if(password) KeyboardType.Password else  KeyboardType.Email)
    )

}