package com.example.ui_component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.example.ui_component.values.appBarFont
import com.example.ui_component.values.largeIcon

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actionIcon: ImageVector,
    onBack: () -> Unit,
    onAction: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = modifier.wrapContentSize(),
            onClick = onBack
        ){
            Icon(
                modifier = modifier.size(largeIcon) ,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "NavIcon",
                tint = Color.White
            )
        }
        Text(text = title, style = TextStyle(color = Color.White), fontSize = appBarFont)
        IconButton(
            modifier = modifier.wrapContentSize(),
            onClick = onAction
        ){
            Icon(
                modifier = modifier.size(largeIcon) ,
                imageVector = actionIcon,
                contentDescription = "Action",
                tint = Color.White
            )
        }
    }
}
