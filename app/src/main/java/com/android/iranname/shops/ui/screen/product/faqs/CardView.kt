package com.android.iranname.shops.ui.screen.product.faqs

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.iranname.shops.db.faqs.ProductFAQsDC
import com.android.iranname.shops.ui.theme.informationText
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.ui.theme.tertiary

@Composable
fun FAQsCardView(faq: ProductFAQsDC) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
            .background(color = tertiary, shape = RoundedCornerShape(8.dp))
            .animateContentSize(
                animationSpec = tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = 200
                )
            ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier.background(color = tertiary)
        ){

            val density = LocalDensity.current

            Text(
                text = faq.question,
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                fontSize = with(density){18.dp.toSp()},
                color = Color(0xFF5D9C59),
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )

            Spacer(
                modifier = Modifier
                    .background(informationText)
                    .height(2.dp)
                    .fillMaxWidth()
            )

            Text(
                text = faq.answer,
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Light,
                fontSize = with(density){16.dp.toSp()},
                color = primary,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}