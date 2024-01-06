package com.android.iranname.literature.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.iranname.R
import com.android.iranname.literature.comment.AddComment
import com.android.iranname.literature.comment.LitCommentScreen

@SuppressLint("DiscouragedApi")
@Composable
fun PoetryPageScreen(poetryName: String, poetName: String) {
    val context = LocalContext.current
    val uri = when (poetName) {
        "فردوسی" -> "ferdosi"
        "عطّار" -> "ataar"
        "بابا طاهر" -> "babataaher"
        "حافظ" -> "haafez"
        "سعدی" -> "sadi"
        else -> ""
    }

    val poetryID = when (poetName) {
        "فردوسی" -> 1
        "عطّار" -> 5
        "بابا طاهر" -> 4
        "حافظ" -> 3
        "سعدی" -> 2
        else -> 0
    }
    val stringResource =
        context.resources.getIdentifier("${uri}_1_poetry", "string", context.packageName)
    val poetry = context.resources.getString(stringResource)
    LazyColumn(
        Modifier
            .paint(
                painter = painterResource(id = R.drawable.literaturebackground),
                contentScale = ContentScale.FillBounds,
//                colorFilter = ColorFilter.tint(
//                    Color.White,
//                    BlurEffect(
//                    radiusX = 5.dp,
//                    radiusY = 5.dp,
//                    edgeTreatment = TileMode(RoundedCornerShape(8.dp))))
            )
    ) {
        item {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier.fillMaxSize(),
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.literaturebackground),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .blur(
//                            radiusX = 5.dp,
//                            radiusY = 5.dp,
//                            edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
//                        )
//                        .fillMaxSize(),
//                    contentScale = ContentScale.FillBounds
//                )
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = poetry,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
//                        .weight(2f)
                )

                LitCommentScreen(poetryID)
                AddComment(literature_id = poetryID)

            }
//            }
        }
    }
}