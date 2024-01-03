package com.android.iranname.shops.ui.screen.product

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.model.screens.ShopScreens
import com.android.iranname.shops.ui.theme.informationText
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.ui.theme.tertiary
import io.github.giangpham96.expandable_text_compose.ExpandableText

@Composable
fun ProductCardView(productsDC: ProductsDC , navController: NavController) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
            .background(color = tertiary, shape = RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate(ShopScreens.ProductScreen.passInfo(productsDC))
            }
            .animateContentSize(
                animationSpec = tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = 200
                )
            ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            Modifier.background(color = tertiary)
        ){
            Image(
                painter = rememberAsyncImagePainter(productsDC.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .height(300.dp)
                    .fillMaxWidth()
            )

            val density = LocalDensity.current

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = productsDC.price.toString() + "Toman",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = with(density){16.dp.toSp()},
                    color = Color(0xFF5D9C59),
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                )

                Text(
                    text = productsDC.name,
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = with(density){20.dp.toSp()},
                    color = primary,
                    modifier = Modifier
                        .padding(10.dp)

                )

            }

            var expand by remember {
                mutableStateOf(false)
            }

            ExpandableText(
                originalText = productsDC.description,
                style = TextStyle(
                    textAlign = TextAlign.Right
                ),
                expandAction = "بیشتر",
                expandActionColor = primary,
                expand = expand,
                fontSize = with(density){16.dp.toSp()},
                color = informationText,
                fontFamily = FontFamily.SansSerif,
                limitedMaxLines = 2,
                animationSpec = spring(),
                modifier = Modifier
                    .clickable { expand = !expand }
                    .padding(10.dp)
            )
        }
    }
}