package com.android.iranname.shops.ui.screen.product

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.account.viewModel.basket.ShopBasketVM
import com.android.iranname.commonServices.ui.compose.reload.Reload
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.screen.product.faqs.FAQsCardView
import com.android.iranname.shops.ui.theme.informationText
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.viewmodel.faqs.ProductFAQsVM

@Composable
fun ShopProductScreen(product: ProductsDC,navController: NavController){
    Column(
       modifier =  Modifier.verticalScroll(rememberScrollState())
    )  {

        val viewModel = viewModel(ProductFAQsVM::class.java)
        val context = LocalContext.current

        Card(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp, start = 10.dp)
                .border(width = 5.dp, color = Color(0xFF5D9C59)),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .height(300.dp)
                    .fillMaxWidth()
            )
        }


        val density = LocalDensity.current

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = product.price.toString() + " T",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                fontSize = with(density){16.dp.toSp()},
                color = Color(0xFF5D9C59),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            )

            Text(
                text = product.name,
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                fontSize = with(density){20.dp.toSp()},
                color = primary,
                modifier = Modifier
                    .padding(10.dp)

            )

        }

        var descriptionExpandedState by remember { mutableStateOf(false) }
        val rotationState by animateFloatAsState(
            targetValue = if(descriptionExpandedState) 180f else 0f,
            label = ""
        )

        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        easing = LinearOutSlowInEasing,
                        durationMillis = 200
                    )
                )

                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    descriptionExpandedState = !descriptionExpandedState
                }
                .padding(start = 10.dp, top = 10.dp, bottom = 20.dp, end = 10.dp),
            shape  = RoundedCornerShape(5.dp) ,
            elevation = 2.dp
        ) {

            Column(
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
            ) {

                Row {

                    Text(
                        text = "توضیحات محصول",
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        color = primary,
                        textAlign = TextAlign.Right,
                        fontSize = with(density){18.dp.toSp()}
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDropDown",
                        tint = primary,
                        modifier = Modifier
                            .rotate(rotationState)
                            .padding(5.dp)
                            .size(25.dp)
                    )
                }

                if (descriptionExpandedState) {
                    Text(
                        text = product.description,
                        modifier = Modifier
                            .padding(5.dp),
                        color = informationText,
                        textAlign = TextAlign.Right,
                        fontSize = with(density){18.dp.toSp()}
                    )
                }
            }
        }

        val faqs by viewModel.faqs.collectAsState()
        val loadingState by viewModel.loadStatus.collectAsState()

        viewModel.loadProductsFaqs(
            context = context,
            productId = product.id
        )

        if(loadingState == "loading"){
            Reload()
        } else if(loadingState == "exception") {
            Toast.makeText(context , "خطای دیتابیس داخلی", Toast.LENGTH_SHORT/10).show()
        } else {
            LazyRow{
                items(faqs){it ->
                    FAQsCardView(
                        faq = it
                    )
                }
            }
        }

        val basketViewModel = viewModel(ShopBasketVM::class.java)
        val addToBasketStatus = basketViewModel.addToBasketStatus.collectAsState()
        val gradient = Brush.horizontalGradient(listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)))
        val modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
        Button(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(),
            onClick = {
                if (addToBasketStatus.value != "success"){
                    basketViewModel.addToBasket(
                        context = context,
                        productsDC = product
                    )
                }else{
                    Toast.makeText(context,"این محصول قبلا به سبد خرید شما افزوده شده",Toast.LENGTH_SHORT/10).show()
                }

            },
        ) {
            Box(
                modifier = Modifier
                    .background(gradient)
                    .then(modifier),
                contentAlignment = Alignment.Center,
            ) {
                if(addToBasketStatus.value == ""){
                    Row {

                        Icon(
                            Icons.Default.ShoppingBasket,
                            modifier = Modifier
                                .size(30.dp),
                            contentDescription = "",
                            tint = Color.White
                        )

                        Text(
                            text = "افزودن به سبد خرید",
                            modifier = Modifier
                                .padding(5.dp),
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = with(density){18.dp.toSp()}
                        )

                    }
                }else if(addToBasketStatus.value == "loading"){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(30.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Reload()
                    }
                }else if(addToBasketStatus.value == "success"){
                    Row {
                        Icon(
                            Icons.Default.Done,
                            modifier = Modifier
                                .size(30.dp),
                            contentDescription = "",
                            tint = Color.White
                        )

                        Text(
                            text = "محصول مورد نظر به سبد شما افزوده شد",
                            modifier = Modifier
                                .padding(5.dp),
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = with(density){18.dp.toSp()}
                        )
                    }
                }else{
                    Text(
                        text = "مشکلی پیش آمده لطفا دوباره امتحان کنید",
                        modifier = Modifier
                            .padding(5.dp),
                        color = Color.White,
                        textAlign = TextAlign.Right,
                        fontSize = with(density){18.dp.toSp()}
                    )
                }
            }
        }

    }


}