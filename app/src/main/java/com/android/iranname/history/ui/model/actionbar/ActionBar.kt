package com.android.iranname.history.ui.model.actionbar
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.android.iranname.history.ui.theme.primary
import com.android.iranname.history.ui.theme.secondary
import com.android.iranname.mainmenu.ui.MainActivity


@Composable
fun AppActionBar(title: String){

    val context = LocalContext.current
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val halfWidth = configuration.screenWidthDp / 2.5

    TopAppBar(
        elevation = 1.dp,
        title = {
            Text(
                text = title,
                color = secondary,
                style = MaterialTheme.typography.titleMedium,
                fontSize = with(density){25.dp.toSp()},
                modifier = Modifier
                    .padding(start = halfWidth.dp)
            )
        } ,
        backgroundColor =  primary,
        contentColor = secondary ,

        actions = {
            Icon(
                imageVector = Icons.Default.Home,
                tint = secondary,
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
            )
        }
    )
}