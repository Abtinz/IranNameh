package com.android.iranname.traditions.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.R
import com.android.iranname.landmarks.uitls.LandmarksImagesURI
import com.android.iranname.traditions.ui.model.screen.TraditionScreen
import com.android.iranname.traditions.ui.theme.mainBackGround
import com.android.iranname.traditions.ui.theme.secondary

@Composable
fun CityScreen(navHostController: NavHostController) {
    val cityList = listOf(
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
        stringResource(id = R.string.hamedan),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(mainBackGround)
    ){
        items(cityList.size) { index ->
            cityList[index]?.let {
                cityCard(it, navHostController)
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun cityCard(cityName: String, navController: NavHostController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .width(220.dp)
            .wrapContentHeight()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                navController.navigate(TraditionScreen.Tradition.passInfo(cityName))
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val uri = when (cityName) {
                "همدان" -> "hamedan"
                else -> ""
            }

            Image(
                painter = rememberAsyncImagePainter(model = LandmarksImagesURI.Hegmataneh_First_URI),
                contentDescription = cityName,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
            )

            val density = LocalDensity.current
            Text(
                cityName,
                color = secondary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                fontSize = with(density){20.dp.toSp()},
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
