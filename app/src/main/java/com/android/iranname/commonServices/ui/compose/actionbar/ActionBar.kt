package com.android.iranname.commonServices.ui.compose.actionbar

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.iranname.R
import com.android.iranname.account.AccountActivity
import com.android.iranname.mainmenu.ui.theme.primary
import com.android.iranname.mainmenu.ui.theme.secondary


@Composable
fun HomeActionBar(){
    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val halfWidth = configuration.screenWidthDp.toInt() / 3
    TopAppBar(
        elevation = 1.dp,
        title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = secondary,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = with(density){40.dp.toSp()},
                    modifier = Modifier
                        .padding(start = halfWidth.dp)
                )
        } ,
        backgroundColor =  primary,
        contentColor = secondary ,

        actions = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                tint = secondary,
                contentDescription = stringResource(id = R.string.title_activity_account),
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        context.startActivity(Intent(context, AccountActivity::class.java))
                    }
            )
        }
    )
}

//@Composable
//fun AppActionBar(
//    scope: CoroutineScope,
//    scaffoldState: ScaffoldState,
//    title: String,
//){
//    val context = LocalContext.current
//    TopAppBar(
//        elevation = 1.dp,
//        title = { Text(text = title) } ,
//        backgroundColor =  colorResource(id = R.color.primary),
//        contentColor = colorResource(id = R.color.onPrimary) ,
//        navigationIcon = {
//            IconButton(onClick = {
//                scope.launch {
//                    scaffoldState.drawerState.open()
//                }
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Drawer Bottom"
//                )
//            }
//        } ,
//
//        actions = {
//            Icon(
//                imageVector = Icons.Default.Home,
//                tint = colorResource(id = R.color.onPrimary),
//                contentDescription = "Home",
//                modifier = Modifier
//                    .size(35.dp)
//                    .clickable {
//                        context.startActivity(Intent(context , FirstPageActivity::class.java))
//                    }
//            )
//        }
//    )
//}