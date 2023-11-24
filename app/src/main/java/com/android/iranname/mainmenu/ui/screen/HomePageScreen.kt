package com.android.iranname.mainmenu.ui.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.android.iranname.R
import com.android.iranname.commonServices.network.url.IconsURI
import com.android.iranname.history.HistoryActivity
import com.android.iranname.landmarks.LandmarksActivity
import com.android.iranname.literature.LiteratureActivity
import com.android.iranname.mainmenu.ui.HomePageCategoriesCardView
import com.android.iranname.mainmenu.ui.theme.tertiary
import com.android.iranname.setting.SettingActivity
import com.android.iranname.shops.ShopActivity
import com.android.iranname.traditions.TraditionsActivity

@Composable
fun HomePageScreen() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = tertiary),
        contentAlignment = Alignment.Center
    ){
        LazyColumn{
            item {

                Row{

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_activity_landmarks) ,
                        image = IconsURI.HISTORICAL_LANDMARKS_ICON,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, LandmarksActivity::class.java)
                        )
                    }

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_activity_litratuer) ,
                        image = IconsURI.LITERATURE_ICON_URI,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, LiteratureActivity::class.java)
                        )
                    }
                }

                Row{

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_activity_history) ,
                        image = IconsURI.History_ICON_URI,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, HistoryActivity::class.java)
                        )
                    }

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_activity_traditions) ,
                        image = IconsURI.TRADITIONS_ICON_URI,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, TraditionsActivity::class.java)
                        )
                    }
                }

                Row{

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_activity_shop) ,
                        image = IconsURI.SHOP_ICON_URI,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, ShopActivity::class.java)
                        )
                    }

                    HomePageCategoriesCardView(
                        title = stringResource(id = R.string.title_Setting) ,
                        image = IconsURI.Setting_ICON_URI,
                        modifier = Modifier.weight(1f),
                    ){
                        context.startActivity(
                            Intent(context, SettingActivity::class.java)
                        )
                    }
                }
            }
        }
    }
}