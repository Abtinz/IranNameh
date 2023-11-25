package com.android.iranname.literature.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.iranname.R

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
    val stringResource =
        context.resources.getIdentifier("${uri}_1_poetry", "string", context.packageName)
    val poetry = context.resources.getString(stringResource)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.literaturebackground),
            contentDescription = null,
            modifier = Modifier.blur(
                radiusX = 5.dp,
                radiusY = 5.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
            ).fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = poetry,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}