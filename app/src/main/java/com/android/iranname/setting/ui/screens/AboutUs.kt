package com.android.iranname.setting.ui.screens

import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.iranname.landmarks.ui.theme.informationText
import com.android.iranname.mainmenu.ui.theme.primary
import com.android.iranname.setting.SettingsViewModel
import com.android.iranname.shops.ui.theme.tertiary
import io.github.giangpham96.expandable_text_compose.ExpandableText

@Composable
fun AboutUsSettingScreen() {
    val viewModel: SettingsViewModel = viewModel()
    viewModel.aboutUs()

    val aboutState by viewModel.aboutApiStatus.collectAsState()
    val aboutUs = listOf(
        AboutUs(
            "هدف",
            """
        هدف برنامه ایران‌نامه، ایجاد تعامل مردم ایران با هویت ملی خود و افزایش آگاهی و ارتباط بیشتر با آن است.
         این برنامه با ارائه اطلاعات تاریخی، ادبیاتی، فرهنگی و ... به مردم ایران و سایر کشورها، به دستیابی به دانش بیشتری درباره هویت مشترک و غنی ایرانی کمک می‌کند.
    """.trimIndent()
        ),
        AboutUs(
            "ویژگی\u200Cه",
            """
                برنامه ایران‌نامه، فضایی تعاملی در زمینه‌های تاریخ، ادبیات فارسی، فرهنگ و آداب و رسوم فراهم می‌کند.
                 افراد می‌توانند اطلاعات خود را به اشتراک بگذارند، کالاهای سنتی ایرانی را بخرند و در بخش‌های تعاملی سوالات و نظرات خود را مطرح کنند.
                  تاریخ سلسله‌های ایران هم در بخش تاریخ دردسترس است.
                   در نهایت، از آداب و رسوم شهر‌های مختلف ایران نیز می‌توانند اطلاعات کسب کنند.
            """.trimIndent()
        ),
        AboutUs(
            "ویژگی بارز",
            """
        نمایشی از فرهنگ و تاریخ و ادبیات ایران به دیگر کشورها در جهت آشنایی دیگر ملل با کشورمان و پاسداری از آنچه هویت حقیقی ایران و ایرانی می‌باشد.
         در سال‌های اخیر با ظهور خرده فرهنگها و رسانه‌های اجتماعی، شاهد شدت گرفتن کمرنگ شدن فرهنگ کهن ملی و کم شدن اطلاعات عموم مردم از آنچه امروز تمامیت ارضی ایران را حفظ کرده است، یعنی زبان غنی فارسی و تاریخ مشترک اقوام ایرانی می‌باشیم.
          این برنامه در تالش خواهد بود تا مرهمی بر این شکاف در نسل نوین اطلاعات باشد.
           ویژگی بسیار بارز این برنامه،تجمیع اکثر مواردی است که ارتباط مستقیمی با فرهنگ و تاریخ ایران دارند.
            فروشگاهی از کالاهای سنتی و دست‌ساز ایرانی، تاریخچه مختصری از حکومت‌های کشور، گنجینه‌ای از آداب و رسوم شهرهای مختلف ایران، اطلاعات گردشگری بناهای تاریخی و اشعار غنی فارسی مواردی هستند که هی در یک اپلیکیشن به کاربران ارائه می‌شوند.
    """.trimIndent()
        )
    )
    LazyColumn {
        item {
            aboutUs.forEach {
                AboutItem(title = it.title, text = it.text)
            }
        }
    }
}

@Composable
fun AboutItem(title: String, text: String) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .width(220.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        backgroundColor = tertiary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = title + "\n",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(10.dp),
//                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.End,
            )
            var expand by remember {
                mutableStateOf(false)
            }
            val density = LocalDensity.current


            ExpandableText(
                originalText = text,
                style = TextStyle(
                    textAlign = TextAlign.End
                ),
                expandAction = "See More",
                expandActionColor = primary,
                expand = expand,
                fontSize = with(density) { 16.dp.toSp() },
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

data class AboutUs(
    val title: String,
    val text: String
)