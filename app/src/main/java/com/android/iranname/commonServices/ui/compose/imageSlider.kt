package com.android.iranname.commonServices.ui.compose


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.android.iranname.landmarks.ui.theme.secondary
import kotlinx.coroutines.launch

//this slider is used in shop product and projects and post of idea bank and hiring and profile
//this function will implement our app slider image by its pages circular bullets and coil image loader
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(images: List<String>) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth()
            .height(200.dp)
    ){
        val pagerState = rememberPagerState(pageCount = { images.size })
        val scope = rememberCoroutineScope()
        HorizontalPager(
            state = pagerState,
            key = { images[it] },
            pageSize = PageSize.Fill
        ) { index ->
            Image(
                painter = rememberAsyncImagePainter(model = images[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }

        Box(
            modifier = Modifier
                .offset(y = -(16).dp)
                .clip(RoundedCornerShape(75))
                .align(Alignment.BottomCenter),

            contentAlignment = Alignment.BottomCenter

        ) {
            Row {
                images.forEachIndexed { index, _ ->

                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = null,
                        tint = if (index == pagerState.currentPage) {
                            Color.DarkGray
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(16.dp)
                            .border(
                                width = 1.dp,
                                color = secondary,
                                shape = RoundedCornerShape(50)
                            )
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        page = index
                                    )
                                }
                            }
                    )
                }

            }
        }
    }
}