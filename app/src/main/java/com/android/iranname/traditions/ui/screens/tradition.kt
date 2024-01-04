package com.android.iranname.traditions.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.giangpham96.expandable_text_compose.ExpandableText

@SuppressLint("DiscouragedApi")
@Composable
fun TraditionScreen(cityName: String) {

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            Text(
                cityName + "\n",
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
                    ExpandableText(
                        originalText = "مراسم ازدواج همدان در گذشته شامل موارد خاصی بود که امروزه بیشتر آنها تقریباً منسوخ شده است.این مراسم با خواستگاری (خازمنی کنان) شروع می\u200Cشد و پس از آن نوبت بله برون،رخت بران،حنابندان،رخت داماد،عقدکنان و گرداندن عروس،شب عروسی وپاتختی روز بعد از عروسی و داماد سلام بود.هریک از این مراسم به تبعیت از سنت\u200Cهای هر روستا یا شهر انجام می\u200Cشد.\n" +
                                "خواستگاری:برای انجام مراسم خواستگاری چند نفر از بزرگان به خانه عروس می\u200Cروند و مساله خواستگاری را مطرح می\u200Cکنند.\n" +
                                "بله برون:بعد از مراسم خواستگاری و دریافت جواب مثبت،تعدادی از فامیل درجه یک به خانه عروس می\u200Cروند و در مورد تعیین مهریه و چگونگی برگزاری مراسم و نوع و مقدار خرید برای عروس صحبت می\u200Cکنند و به توافق می\u200Cرسند.\n" +
                                "رخت بران: چند نفر از زنان فامیل برای اندازه گیری و دوختن لباس رای عروس به خانه عروس می\u200Cروند و مقدار زیادی پارچه و شیرینی\u200Cهای مختلف چون نقل و نبات همراه می\u200Cبرند.\n" +
                                "حنابندان: یک شب قبل از عروسی وبا شرکت جوانان فامیل برگزار می\u200Cشود.عروس از این حنا به دست تمام مدعوین می\u200Cزند.\n" +
                                "رخت داماد: صبح روز عروسی تعدادی از جوانان پسر و دوستان داماد به گرد وی جمع می\u200Cشوند و تحفه\u200Cها و لباسهایی را که از طرف خانواده عروس برای او فرستاده شده،به داماد نشان می\u200Cدهندو از میان لباس دامادی را به تن او می\u200Cکنند.\n" +
                                "عقد کنان: مراسم عقدکنان قبل از عروسی بر سر سفره عقد انجام می\u200Cشودو تعدادی از دختران مجرد سفره\u200Cای بربالای سر عروس می\u200Cگیرند و بر سر عروس قند می\u200Cسابند و آرزوی برکت وشیرینی برای زندگی آینده عروس می\u200Cکنند.\n" +
                                "عروس چرخان: شب عروسی تعدادی از بزرگان و فامیل داماد به خانه عروس می\u200Cروند و دو نفر از بزرگان،عروس را از زیر قرآن رد می\u200Cکنند و با صلوات از خانه بیرون می\u200Cآورند و با شادی و سرور به خانه بخت می\u200Cبرند.\n" +
                                "پاتختی: فردای روز عروسی فامیل و زنان کهنسال عروس به دیدن او می\u200Cروند و تحفه\u200Cهایی را که تدارک دیده اند به عنوان هدیه برای عروس می\u200Cبرند.همچنین مرسوم است که از طرف خانواده عروس شیرین پلو و شیرینی برای عروس برده شود.\n" +
                                "داماد سلام: چند روز بعد که معمولاً بین 10 تا 15 روز است،خانواده عروس،زوج جوان را به عنوان پاگشا به خانه خود دعوت می\u200Cکنند.در این مهمانی هدیه\u200Cای به آنها تقدیم می\u200Cشود.\n" +
                                "شب هفت نوزادان:از جشنهای بسیار مرسوم در سطح استان جشن شب هفت نوزاد می\u200Cباشد. این مراسم همراه با میهمانی برگزار می\u200Cشود. به هنگام اذان مغرب، اذان و اقامه را همراه با شهادتین در گوش نوزاد می\u200Cخوانند و نامهایی را که برای او برگزیده اند در لای اوراق قرآن کریم قرار داده و سپس اسمی را که از لای ورق قرآن مجید بیرون آید، روی نوزاد می\u200Cگذارند.",
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
    }
}