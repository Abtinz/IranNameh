package com.android.iranname.history

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.history.model.History

@Composable
fun HistoryCard(spot: History, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val context2 = LocalContext.current
    var isCardClicked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(10.dp)
            .clickable { isCardClicked = !isCardClicked } // Toggle isCardClicked on click
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(16.dp)
        ) {
            val uri = when (spot.dynasityName) {
                "پهلوی (۱۹۲۶ تا ۱۹۷۹ میلادی)" -> "pahlavi"
                "قاجاریان (۱۷۹۴ تا ۱۹۲۶ میلادی)" -> "ghajar"
                "زندیان (۱۷۵۰ تا ۱۷۹۴ میلادی)"->"zandian"
                "افشاریان (۱۷۳۵ تا ۱۷۵۰ میلادی)"-> "afshar"
                "صفویان (۱۵۰۲ تا ۱۷۳۵ میلادی)"->"safavi"
//                "خوارزمشاهیان (۱۱۵۳ تا ۱۲۳۱ میلادی)"->""
//                "سامانیان (۸۱۹ تا ۱۰۰۴ میلادی)"->""
                "ساسانیان (۲۲۴ تا ۶۵۱ میلادی)"->"sasani"
//                "اشکانیان (۲۵۰پیش از میلاد تا ۲۲۶میلادی)"
                "هخامنشیان (۵۵۹ تا ۳۳۰ پیش از میلاد- مدت حکومت ۲۲۹ سال)"->"hakhamaneshian"
//                "پادشاهی ماد (از ح۷۰۰ تا ح۵۵۰ پیش از میلاد)"->""
                else -> ""
            }
            val uri2 = when (spot.dynasityName) {
//                "پهلوی (۱۹۲۶ تا ۱۹۷۹ میلادی)" -> "pahlavi2"
                "قاجاریان (۱۷۹۴ تا ۱۹۲۶ میلادی)" -> "ghajar2"
                "زندیان (۱۷۵۰ تا ۱۷۹۴ میلادی)"->"zandian2"
                "افشاریان (۱۷۳۵ تا ۱۷۵۰ میلادی)"-> "afshar2"
                "صفویان (۱۵۰۲ تا ۱۷۳۵ میلادی)"->"safavi2"
                "خوارزمشاهیان (۱۱۵۳ تا ۱۲۳۱ میلادی)"->"kharazmshah2"
//                "سامانیان (۸۱۹ تا ۱۰۰۴ میلادی)"->"samanid2"
                "ساسانیان (۲۲۴ تا ۶۵۱ میلادی)"->"sasani2"
                "اشکانیان (۲۵۰پیش از میلاد تا ۲۲۶میلادی)"->"ashkanian2"
                "هخامنشیان (۵۵۹ تا ۳۳۰ پیش از میلاد- مدت حکومت ۲۲۹ سال)"->"hakhamaneshian2.png"
//                "پادشاهی ماد (از ح۷۰۰ تا ح۵۵۰ پیش از میلاد)"->""
                else -> ""
            }
//            Log.d("MainActivity", uri)
            Text(
                text = spot.dynasityName,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Row {

// Check if the resource is not null before using it
                if (uri != "") {
                    val imageResource = context.resources.getIdentifier("@drawable/$uri", null, context.packageName)
                    val res = context.resources.getDrawable(imageResource, context.theme)

                    Image(
                        painter = rememberAsyncImagePainter(model = res),
                        contentDescription = spot.description,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                } else {
                    // Handle the case when the resource is null or not found
                    // You can log a message or take appropriate action here
                    Log.e("MainActivity", "Resource not found for uri: $uri")
                }

//                Spacer(modifier = Modifier.width(16.dp))
            }

            if (isCardClicked) {
                Text(
                    text = spot.description,
                    textAlign = TextAlign.Right,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(120.dp))
                    if (uri2 != "") {
                        val imageResource2 = context.resources.getIdentifier(
                            "@drawable/$uri2",
                            null,
                            context.packageName
                        )
                        Log.d("MainActivity", uri2)
                        val res2 = context.resources.getDrawable(imageResource2, context.theme)
                        Log.d("MainActivity", "h2")
                        Image(
                            painter = rememberAsyncImagePainter(model = res2),
                            contentDescription = spot.description,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                        )
                    } else {
                        // Handle the case when the resource is null or not found
                        // You can log a message or take appropriate action here
                        Log.e("MainActivity", "Resource not found for uri: $uri2")
                    }
                }
            }
        }
    }
}
