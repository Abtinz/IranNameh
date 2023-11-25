package com.android.iranname.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.iranname.history.model.History
import com.android.iranname.history.ui.theme.IranNameTheme

class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IranNameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    HistoryList(spots = listOf<History>(
                        HistoryItems.pahlavi,
                        HistoryItems.ghajarian,
                        HistoryItems.afsharian,
                        HistoryItems.zandian,
                        HistoryItems.safavian
                    ))
                }
            }
        }
    }
}

@Composable
fun HistoryList(spots: List<History>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier){
        items(spots){spot ->
            HistoryCard(
                spot = spot,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}