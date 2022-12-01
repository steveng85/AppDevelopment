package com.example.appdevelopment.ui.components

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.sharp.Mail
import androidx.compose.material.icons.sharp.MailOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.appdevelopment.R

@Composable
fun DefaultFieldBox(
    currentValue: String,
    onEvent: (String) -> Unit,
    focusedColor: Color,
    unfocusedColor: Color,
    label: String,
    type: String,
    password: Boolean
){

    var passwordVisible by remember { mutableStateOf(password) }

    OutlinedTextField(
        value = currentValue,
        onValueChange = {
            onEvent(it)
                        },
        label = { Text(label) },
        trailingIcon = {
            if(type == "email"){
                Image(painter = painterResource(id = R.drawable.ic_baseline_email_24), contentDescription = null)
            } else if(type == "username") {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_person_outline_24),
                    contentDescription = null,
                )


            }else if(type == "description"){
                Image(painter = painterResource(id = R.drawable.ic_baseline_description_24), contentDescription = null)
            }else {
            if(passwordVisible) {
           IconButton(onClick = { passwordVisible = false }) {
               Image(painter = painterResource(id = R.drawable.ic_baseline_visibility_off_24), contentDescription = null)
           }
            }else {
           IconButton(onClick = { passwordVisible = true }) {
               Image(
                   painter = painterResource(id = R.drawable.ic_baseline_visibility_24),
                   contentDescription = null
               )
           }
       }}},
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = focusedColor,
            unfocusedBorderColor = unfocusedColor,
            focusedLabelColor = focusedColor,
            unfocusedLabelColor = unfocusedColor,
        ),
        visualTransformation = if (!passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = if(type == "password") KeyboardType.Password else  KeyboardType.Email)
    )

}