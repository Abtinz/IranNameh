package com.android.iranname.traditions.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.commonServices.network.quality.UserNetworkChecker
import com.android.iranname.commonServices.ui.compose.error.network.NetworkIsGoneScreen
import com.android.iranname.commonServices.ui.compose.error.server.SomethingWentWrongScreen
import com.android.iranname.commonServices.ui.compose.reload.Reload
import com.android.iranname.traditions.db.CityTraditionsDC
import com.android.iranname.traditions.ui.model.screen.TraditionScreen
import com.android.iranname.traditions.ui.theme.mainBackGround
import com.android.iranname.traditions.ui.theme.secondary
import com.android.iranname.traditions.ui.theme.tertiary
import com.android.iranname.traditions.viewmodel.TraditionsMainMenuVM

@Composable
fun CityScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val isInternetAvailable = remember{
        mutableStateOf(UserNetworkChecker(context).checkNetwork)
    }
    if(isInternetAvailable.value){
        val viewModel = viewModel(TraditionsMainMenuVM::class.java)

        val cities by viewModel.cities.collectAsState()
        val loadingState by viewModel.loadStatus.collectAsState()

        viewModel.loadCities(
            context = LocalContext.current
        )

        if(loadingState == "loading"){
            Reload()
        } else if(loadingState == "exception") {
            SomethingWentWrongScreen()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .background(mainBackGround)
            ){
                items(cities.size) { index ->
                    cityCard(cities[index], navHostController)
                }
            }
        }
    }else{
        NetworkIsGoneScreen{
            isInternetAvailable.value = true
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun cityCard(city: CityTraditionsDC, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .width(220.dp)
            .clickable{
                navController.navigate(TraditionScreen.Tradition.passInfo(city))
            },
        shape = RoundedCornerShape(0.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(tertiary)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = city.provinceImage),
                contentDescription = city.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(10.dp)
                    .height(130.dp)
                    .fillMaxWidth()
            )

            val density = LocalDensity.current
            Text(
                city.name,
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
