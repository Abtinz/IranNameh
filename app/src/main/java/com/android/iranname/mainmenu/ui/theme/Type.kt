package com.android.iranname.mainmenu.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.iranname.R


val fastanFont = FontFamily(
    Font(R.font.fastan)
)

val shekasteFont = FontFamily(
    Font(R.font.shekaste)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleMedium = TextStyle(
        fontFamily = fastanFont,
        fontWeight = FontWeight.Bold
    ),

    displaySmall = TextStyle(
        fontFamily = shekasteFont,
    ),
)