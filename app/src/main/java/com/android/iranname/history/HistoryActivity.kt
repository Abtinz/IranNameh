package com.android.iranname.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.iranname.R
import com.android.iranname.history.model.History
import com.android.iranname.history.ui.model.actionbar.AppActionBar
import com.android.iranname.history.ui.theme.HistoryTheme
import com.android.iranname.history.ui.theme.mainBackGround

class HistoryActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HistoryTheme {
                HistoryPageSetUp()
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    HistoryList(spots = listOf<History>(
//                        HistoryItems.pahlavi,
//                        HistoryItems.ghajarian,
//                        HistoryItems.afsharian,
//                        HistoryItems.zandian,
//                        HistoryItems.safavian
//                    ))
//                }
            }
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun HistoryPageSetUp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppActionBar(title = stringResource(id = R.string.title_activity_history))
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
        ) {
//            LandmarkNavGraph(navHostController = navController )
            HistoryList(spots = listOf<History>(
                HistoryItems.pahlavi,
                HistoryItems.ghajarian,
                HistoryItems.afsharian,
                HistoryItems.zandian,
                HistoryItems.safavian,
                HistoryItems.kharazmshahian,
                HistoryItems.samanian,
                HistoryItems.sasanian,
                HistoryItems.ashkanian,
                HistoryItems.hakhamaneshian,
                HistoryItems.mad
            ))
        }

    }
}

@Composable
fun HistoryList(spots: List<History>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .background(color = mainBackGround)
    ){
        items(spots){spot ->
            HistoryCard(
                spot = spot,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}