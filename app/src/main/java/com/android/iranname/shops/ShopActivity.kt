package com.android.iranname.shops
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.android.iranname.R
import com.android.iranname.commonServices.ui.compose.actionbar.AppActionBar
import com.android.iranname.shops.ui.model.navigation.ShopNavGraph
import com.android.iranname.shops.ui.theme.ShopsTheme


class ShopActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopsTheme {
                ShopPageSetUp()
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun ShopPageSetUp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppActionBar(title = stringResource(id = R.string.title_activity_shop))
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
            ShopNavGraph(navHostController = navController )
        }
    }
}