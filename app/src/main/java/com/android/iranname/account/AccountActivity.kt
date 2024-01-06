package com.android.iranname.account

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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.android.iranname.account.ui.theme.AccountTheme
import com.android.iranname.account.ui.theme.DarkGreen
import com.android.iranname.account.ui.theme.GrayGreen
import com.android.iranname.account.ui.theme.SemiDarkGreen
import com.android.iranname.commonServices.navigation.AccountNavGraph
import com.android.iranname.mainmenu.ui.MainActivity
import com.android.iranname.shops.ui.theme.primary

class AccountActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountTheme {
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
                            AppActionBar("احراز هویت")
                        },
                        drawerElevation = 30.dp,
                    ) { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            AccountNavGraph(navController)
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
            Text(
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
                contentDescription = "Account",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            )
        }
    )
}