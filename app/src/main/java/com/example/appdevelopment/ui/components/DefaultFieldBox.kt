package com.example.appdevelopment.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun DefaultFieldBox(value: String, focusedColor: Color, unfocusedColor: Color, label: String, password: Boolean){

    /*var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }*/
    var passwordVisible by remember { mutableStateOf(password) }

    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { label },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedColor,
            unfocusedBorderColor = unfocusedColor,
            focusedLabelColor = focusedColor,
            unfocusedLabelColor = unfocusedColor
        ),
        visualTransformation = if (!passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )

}