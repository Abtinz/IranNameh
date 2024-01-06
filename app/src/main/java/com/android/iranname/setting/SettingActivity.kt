package com.android.iranname.setting

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.android.iranname.account.ui.theme.DarkGreen
import com.android.iranname.account.ui.theme.GrayGreen
import com.android.iranname.account.ui.theme.SemiDarkGreen
import com.android.iranname.history.ui.theme.HistoryTheme
import com.android.iranname.mainmenu.ui.MainActivity
import com.android.iranname.setting.ui.model.SettingNavGraph
import com.android.iranname.shops.ui.theme.primary

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HistoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState =
                        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    val navController = rememberNavController()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            AppActionBar("تنظیمات")
                        },
                        drawerElevation = 30.dp,
                    ) { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            SettingNavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AppActionBar(title: String){

    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val halfWidth = configuration.screenWidthDp / 2.8

    TopAppBar(
        elevation = 1.dp,
        title = {
            androidx.compose.material.Text(
                text = title,
                color = SemiDarkGreen,
                style = MaterialTheme.typography.titleMedium,
                fontSize = with(density){25.dp.toSp()},
                modifier = Modifier
                    .padding(start = halfWidth.dp)
            )
        } ,
        backgroundColor =  primary,
        contentColor = DarkGreen ,

        actions = {
            Icon(
                imageVector = Icons.Default.Home,
                tint = GrayGreen,
                contentDescription = "Settings",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            )
        }
    )
}