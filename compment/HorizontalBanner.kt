package com.jrrzx.emergencyhelper.common

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jrrzx.emergencyhelper.R
import com.jrrzx.emergencyhelper.ext.toJson
import com.jrrzx.emergencyhelper.helper.MediaItemInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class BannerInfo(
    val img: String,
    val title: String,
    val isInit: Boolean = false
)

@Preview
@Composable
fun HorizontalBannerPreview() {
    val isScrollEnabled by remember { mutableStateOf(true) }
    HorizontalBanner(listOf(), autoScrollEnabled = isScrollEnabled, onItemClicked = {
    })
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HorizontalBanner(
    list: List<MediaItemInfo>,
    autoScrollDuration: Long = 3000,
    scrollDurationMillis: Int = 500,
    autoScrollEnabled: Boolean = true,
    onItemClicked: (MediaItemInfo) -> Unit
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { list.size })
    Log.e("xxx", "list.size:${list} ")
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    if (isDragged.not() && autoScrollEnabled) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(
                            page = nextPage,
                            animationSpec = tween(
                                durationMillis = scrollDurationMillis
                            )
                        )
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    HorizontalPager(
        state = pagerState,
//        contentPadding = PaddingValues(horizontal = 6.dp),
//        pageSpacing = 16.dp,
        modifier = Modifier
            .fillMaxWidth()
//            .height(200.dp)
            .fillMaxHeight()
    ) { page: Int ->
        list[page].let {
            Card(
                onClick = { onItemClicked(it) },
                modifier = Modifier.fillMaxSize(),
            ) {
                CarouselBox(it)
            }
        }
    }

}

@Composable
fun CarouselBox(item: MediaItemInfo) {
    Box {
        AsyncImage(
            model = item.filePath,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

//        Text(
//            text = item.title,
//            color = Color.White,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//                .background(Black30)
//                .padding(16.dp)
//        )
    }
}