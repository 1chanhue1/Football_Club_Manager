package com.example.feature_terms.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature_terms.presentation.ui_component.CheckView
import com.example.feature_terms.presentation.ui_component.TopScreenView
import com.example.feature_terms.presentation.ui_component.TotalAgreeView
import com.example.ui_component.CustomGradientButton
import com.example.ui_component.gradientColorsList
import com.example.ui_component.mainTheme

@Composable
fun TermsScreen(
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = mainTheme)
            .padding(40.dp)
    ) {
        TopScreenView()
        TotalAgreeView() {}
        CheckView()
        CustomGradientButton(
            gradientColors = gradientColorsList,
            cornerRadius = 16.dp,
            buttonText = "동의하고 가입하기",
            roundedCornerShape = RoundedCornerShape(20.dp)
        ) {
        }
    }
}

@Preview
@Composable
fun TermScreenPreview() {
    TermsScreen()
}