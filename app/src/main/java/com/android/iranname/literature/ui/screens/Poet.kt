package com.android.iranname.literature.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.literature.ui.model.screen.LiteratureScreen
import io.github.giangpham96.expandable_text_compose.ExpandableText

@SuppressLint("DiscouragedApi")
@Composable
fun PoetPageScreen(poetName: String, navController: NavHostController) {
    val context = LocalContext.current
    val uri = when (poetName) {
        "فردوسی" -> "ferdosi"
        "عطّار" -> "ataar"
        "بابا طاهر" -> "babataaher"
        "حافظ" -> "haafez"
        "سعدی" -> "sadi"
        else -> ""
    }
    val stringResource = context.resources.getIdentifier("${uri}_1", "string", context.packageName)
    val poetryList = listOf(
        context.resources.getString(stringResource),
    )
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            val imageResource =
                context.resources.getIdentifier("@drawable/$uri", null, context.packageName)
            val res = context.resources.getDrawable(imageResource, context.theme)
            Image(
                painter = rememberAsyncImagePainter(model = res),
                contentDescription = poetName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
            )
            Text(
                poetName + "\n",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(5.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Right
            )


            var descriptionExpandedState by remember { mutableStateOf(true) }
            var colors by remember { mutableStateOf(Color.Gray) }

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
                    },
                shape = RoundedCornerShape(0.dp),
            ) {
                if (descriptionExpandedState) {

                    //description section
                    var expand by remember {
                        mutableStateOf(false)
                    }
                    val stringResource2 =
                        context.resources.getIdentifier(uri, "string", context.packageName)
                    ExpandableText(
                        originalText = context.resources.getString(stringResource2),
                        expandAction = "See More",
                        style = TextStyle(
                            textAlign = TextAlign.End
                        ),
                        fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                        expand = expand,
                        expandActionColor = Color.DarkGray,
                        fontSize = 14.sp,
                        color = colors,
                        limitedMaxLines = 2,
                        animationSpec = spring(),
                        modifier = Modifier
                            .clickable {
                                expand = !expand
                                if (colors == Color.Gray) {
                                    colors = Color.Black
                                } else if (colors == Color.Black) {
                                    colors = Color.Gray
                                }
                            }
                            .padding(10.dp)
                    )
                }
            }


        }
        items(poetryList) {
            poetryCard(poetName, it, navController)
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun poetryCard(
    poetName: String,
    poetryName: String,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .wrapContentSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                navController.navigate(
                    LiteratureScreen.Poetry.passInfo(
                        poetName = poetName,
                        poetryName = poetryName
                    )
                )
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                poetryName + "\n",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .shadow(2.dp)
                    .padding(5.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.End,
            )
        }
    }
}
