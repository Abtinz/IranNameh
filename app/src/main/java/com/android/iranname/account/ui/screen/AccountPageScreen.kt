package com.android.iranname.account.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.iranname.R
import com.android.iranname.account.db.User
import com.android.iranname.account.viewModel.basket.ShopBasketVM
import com.android.iranname.commonServices.network.quality.UserNetworkChecker
import com.android.iranname.commonServices.ui.compose.error.server.SomethingWentWrongScreen
import com.android.iranname.commonServices.ui.compose.reload.Reload
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.screen.product.ProductCardView

@Composable
fun AccountPageScreen(user: User?, navHostController: NavHostController) {
    val context = LocalContext.current
    val viewModel: ShopBasketVM = viewModel()
    viewModel.loadBasket(context)
    val basket by viewModel.products.collectAsState()
    val removeStatus by viewModel.removeFromStatus.collectAsState()
    val isInternetAvailable = remember {
        mutableStateOf(UserNetworkChecker(context).checkNetwork)
    }
    if (isInternetAvailable.value) {
        val loadingState by viewModel.loadStatus.collectAsState()

        when (loadingState) {
            "loading" -> {
                Reload()
            }

            "exception" -> {
                SomethingWentWrongScreen()
            }

            else -> {

                LazyColumn {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.literaturebackground),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.CenterHorizontally),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            user?.user_id?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }

                            val removeProduct = remember { mutableStateOf(false) }
                            Spacer(modifier = Modifier.height(16.dp))
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Shop Basket",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            basket.forEach {
                                it.let {
                                    if (removeProduct.value) {
                                        viewModel.removeFromBasket(context, it)
                                        when (removeStatus) {
                                            "loading" -> Reload()
                                            "exception" -> SomethingWentWrongScreen()
                                            else -> {
                                                Toast.makeText(
                                                    context,
                                                    removeStatus,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                        removeProduct.value = false
                                    }
                                    Box(
                                        modifier = Modifier.padding(5.dp)
                                    ) {
                                        ProductCardView(
                                            ProductsDC(
                                                it.name,
                                                it.image,
                                                it.description,
                                                it.price
                                            ), navHostController
                                        )
                                        Button(
                                            onClick = {
                                                removeProduct.value = true
                                            },
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .background(Color.Transparent),
                                            shape = CircleShape,
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color.Transparent,
                                                contentColor = Color.DarkGray,
                                                disabledContentColor = Color.Transparent,
                                                disabledBackgroundColor = Color.Transparent
                                            ),
                                            elevation = ButtonDefaults.elevation(
                                                defaultElevation = 0.dp,
                                                pressedElevation = 0.dp,
                                                disabledElevation = 0.dp,
                                                hoveredElevation = 0.dp,
                                                focusedElevation = 0.dp
                                            )
                                        ) {
                                            androidx.compose.material.Text(
                                                text = "x",
                                                color = Color.Red,
                                                fontSize = 20.sp,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}