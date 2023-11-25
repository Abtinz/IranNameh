package com.android.iranname.literature.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.R
import com.android.iranname.landmarks.ui.theme.informationText
import com.android.iranname.literature.ui.model.screen.LiteratureScreen
import com.android.iranname.mainmenu.ui.theme.primary
import io.github.giangpham96.expandable_text_compose.ExpandableText

@Composable
fun LiteraturePageScreen(navController: NavController) {
    val poetList = listOf(
        stringResource(id = R.string.poet_ferdosi),
        stringResource(id = R.string.poet_sadi),
        stringResource(id = R.string.poet_haafez),
        stringResource(id = R.string.poet_babataaher),
        stringResource(id = R.string.poet_ataar)
    )
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        items(poetList) {
            poetCard(it, navController)
        }
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun poetCard(poetName: String, navController: NavController) {
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
                navController.navigate(LiteratureScreen.Poet.passInfo(poetName))
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val uri = when (poetName) {
                "فردوسی" -> "ferdosi"
                "عطّار" -> "ataar"
                "بابا طاهر"->"babataaher"
                "حافظ"-> "haafez"
                "سعدی"->"sadi"
                else -> ""
            }
            val imageResource = context.resources.getIdentifier("@drawable/$uri", null, context.packageName)
            val res = context.resources.getDrawable(imageResource, context.theme)
            Image(
                painter = rememberAsyncImagePainter(model = res),
                contentDescription = poetName,
                contentScale = ContentScale.FillBounds,
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
            var expand by remember {
                mutableStateOf(false)
            }
            val density = LocalDensity.current

            val stringResource = context.resources.getIdentifier(uri, "string", context.packageName)
            ExpandableText(
                originalText = context.resources.getString(stringResource),
                style = TextStyle(
                    textAlign = TextAlign.End
                ),
                expandAction = "See More",
                expandActionColor = primary,
                expand = expand,
                fontSize = with(density){16.dp.toSp()},
                color = informationText,
                fontStyle = MaterialTheme.typography.labelSmall.fontStyle,
                limitedMaxLines = 2,
                animationSpec = spring(),
                modifier = Modifier
                    .clickable { expand = !expand }
                    .padding(10.dp)
            )
        }
    }
}
