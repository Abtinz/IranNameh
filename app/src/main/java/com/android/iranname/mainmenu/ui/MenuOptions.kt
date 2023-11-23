package com.android.iranname.mainmenu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.mainmenu.ui.theme.primary


@Composable
fun HomePageCategoriesCardView(title: String, image: String, modifier: Modifier,onClick:()->Unit) {
    val density = LocalDensity.current
    Column(
        modifier = modifier
    ){

        Card(
            shape = RoundedCornerShape(0.dp),
            elevation = 5.dp,
            modifier = Modifier
                .size(200.dp)
                .padding(10.dp)
                .clickable {
                    onClick()
                }
        ){
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "" ,
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = title ,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            color = primary,
            textAlign = TextAlign.Center ,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
