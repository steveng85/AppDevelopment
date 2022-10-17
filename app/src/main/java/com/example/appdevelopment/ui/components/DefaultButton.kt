package com.example.appdevelopment.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(onClick: String, text: String, contentColor: Color, containerColor: Color){
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(40.dp),
        modifier = Modifier
            .fillMaxWidth(),
        //.shadow(1.dp, RoundedCornerShape(10.dp), true),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 8.dp,
            disabledElevation = 4.dp,
            hoveredElevation = 4.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        )
    ) {
        Text(text = text)
    }
}