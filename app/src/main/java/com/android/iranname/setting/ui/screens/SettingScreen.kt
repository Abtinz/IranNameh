package com.android.iranname.setting.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.iranname.setting.ui.model.SettingScreens
import com.android.iranname.shops.ui.theme.primary
import com.android.iranname.shops.ui.theme.secondary

@Composable
fun SettingScreen(navController: NavHostController) {
    val settingItems = listOf(
        SettingScreens.Help,
        SettingScreens.AboutUs
        )
    LazyColumn {
        items(items = settingItems,
            itemContent = { settingItem ->
            SettingItemView(item = settingItem, navController)
        }
        )
    }
}
@Composable
fun SettingItemView(item: SettingScreens, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    Card(modifier = Modifier.fillMaxWidth()) {
        Row {
            SettingsItemCard(item = item, selected = currentRoute == item.route, onItemClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
            })
        }
    }
}

@Composable
fun SettingsItemCard(item: SettingScreens, selected: Boolean, onItemClick: (SettingScreens) -> Unit) {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .border(
                border = BorderStroke(width = 0.0625.dp, color = Color.Black)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(5.dp)

        ) {
            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )

            val colors = remember {
                if (selected) {
                    primary
                } else {
                    secondary
                }
            }
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier
                    .size(28.dp),
                tint = colors
            )

            Text(
                text = item.title,
                fontSize = 18.sp,
                color = colors,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp)
            )
        }
    }
}
