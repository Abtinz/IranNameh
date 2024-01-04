package com.android.iranname.shops.ui.screen.product

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.theme.informationText
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.viewmodel.faqs.ProductFAQsVM

@Composable
fun ShopProductScreen(product: ProductsDC,navController: NavController){
    Column(
       modifier =  Modifier.verticalScroll(rememberScrollState())
    )  {

        val viewModel = viewModel(ProductFAQsVM::class.java)

        Card(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp, start = 10.dp)
                .border(width = 5.dp , color = Color(0xFF5D9C59)),
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
                            .padding(5.dp) ,
                        color = informationText,
                        textAlign = TextAlign.Right,
                        fontSize = with(density){18.dp.toSp()}
                    )
                }
            }
        }
    }
}