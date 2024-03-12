package com.example.feature_joinclub.presentation.ui_component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.model.ClubInfo
import com.example.ui_component.HorizontalSpacer
import com.example.ui_component.R
import com.example.ui_component.VerticalSpacer
import com.example.ui_component.template.DefaultItem
import com.example.ui_component.values.horizontalGradation
import com.example.ui_component.values.tinyFont
import com.example.ui_component.values.veryBigFont

@Composable
fun ClubItem(
    selectedIndex: MutableState<Int>,
    index: Int,
    content: @Composable () -> Unit
) {
    DefaultItem(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .selectable(
                selected = selectedIndex.value == index,
                onClick = {
                    if (selectedIndex.value == index)
                        selectedIndex.value = -1
                    else
                        selectedIndex.value = index
                }
            )
            .border(
                width = 2.dp,
                brush = if (selectedIndex.value == index) horizontalGradation else Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF),
                        Color(0xFFFFFFFF)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ),
        radius = 8.dp,
        color = Color.White,
        content = content
    )
}

@Composable
fun ClubContent(club: ClubInfo) {
    Row(
        Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier) {
            Text(text = club.teamName, fontWeight = FontWeight.Bold, fontSize = veryBigFont)
            VerticalSpacer(value = 10)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
                HorizontalSpacer(value = 5)
                Text(text = "${club.totalMemberCnt} 명", fontSize = tinyFont)
            }
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(imageVector = Icons.Default.Person, contentDescription = "")
//                HorizontalSpacer(value = 5)
//                Text(text = club.introduction, fontSize = tinyFont)
//            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = club.emblem,
                contentDescription = "profile image",
                error = painterResource(id = R.drawable.default_profile_image),
                placeholder = painterResource(
                    id = R.drawable.default_profile_image
                ),
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier,
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Icon"
            )
        }
    }
}

@Composable
@Preview
fun ClubItemPreview() {
    ClubItem(selectedIndex = mutableStateOf(1), index = 1) {
        ClubContent(club = ClubInfo(3, "teamname", 20, "unique", ""))
    }
}