package com.android.iranname.shops.ui.screen.product

import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.shops.model.ProductsDC
import com.android.iranname.shops.ui.theme.informationText
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.ui.theme.secondary
import com.android.iranname.shops.viewmodel.faqs.ProductFAQsVM
import io.github.giangpham96.expandable_text_compose.ExpandableText

@Composable
fun ShopProductScreen(product: ProductsDC,navController: NavController) {

    val viewModel = viewModel(ProductFAQsVM::class.java)

    Card(
        modifier = Modifier
            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
            .border(width = 5.dp , color = secondary),
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

    var expand by remember {
        mutableStateOf(false)
    }


    ExpandableText(
        originalText = product.description,
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
