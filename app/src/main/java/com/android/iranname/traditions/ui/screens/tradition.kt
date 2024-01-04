package com.android.iranname.traditions.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.commonServices.ui.compose.ImageSlider
import com.android.iranname.traditions.db.CityTraditionsDC
import com.android.iranname.traditions.ui.theme.informationText
import com.android.iranname.traditions.ui.theme.primary

@Composable
fun CityTraditionsScreen(city:CityTraditionsDC) {
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ){
        val density = LocalDensity.current

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = city.name,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            fontSize = with(density){24.dp.toSp()},
            color = primary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        Image(
            painter = rememberAsyncImagePainter(city.provinceImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .height(300.dp)
                .fillMaxWidth()
        )

        Text(
            text = city.provinceDescription,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            fontSize = with(density){16.dp.toSp()},
            color = informationText ,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "پوشش مردم(سنتی و محلی)",
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            fontSize = with(density){24.dp.toSp()},
            color = primary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        Image(
            painter = rememberAsyncImagePainter(city.localCustomsImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .height(300.dp)
                .fillMaxWidth()
        )

        Text(
            text = city.cutomsDescription,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            fontSize = with(density){16.dp.toSp()},
            color = informationText ,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "آداب و رسوم",
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.ExtraBold,
            fontSize = with(density){24.dp.toSp()},
            color = primary,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

        ImageSlider(images = listOf(
                city.traditionsImage,
                city.secondTraditionsImage,
                city.thirdTraditionsImage,
                city.fourthTraditionsImage,
            )
        )

        Text(
            text = city.traditionsDescription,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            fontSize = with(density){16.dp.toSp()},
            color = informationText ,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        )

    }
}
