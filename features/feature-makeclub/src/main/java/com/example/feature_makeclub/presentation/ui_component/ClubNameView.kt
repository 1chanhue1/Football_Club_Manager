package com.example.feature_makeclub.presentation.ui_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_component.VerticalSpacer

@Composable
fun ClubNameView() {
    Column {
        Text(text = "구단 이름", modifier = Modifier.padding(start = 10.dp), color = Color.White)
    }
    VerticalSpacer(value = 15)
}

@Preview
@Composable
fun ClubNameViewPreview() {
    ClubNameView()
}