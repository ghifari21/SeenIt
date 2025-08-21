package com.gosty.home.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.gosty.common.constant.BaseUrl
import com.gosty.home.models.BannerItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroBanner(
    modifier: Modifier = Modifier,
    items: List<BannerItem>
) {
    if (items.isEmpty()) return

    val initialPage = Int.MAX_VALUE / 2
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(pagerState) {
        while (true) {
            try {
                snapshotFlow { pagerState.isScrollInProgress }
                    .first { !it }

                while (true) {
                    delay(3000L)
                    val nextPage = pagerState.currentPage + 1
                    pagerState.animateScrollToPage(nextPage)
                }
            } catch (e: CancellationException) {
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 350.dp)
            .clip(shape = RoundedCornerShape(size = 8.dp))
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val itemIndex = (page - initialPage).mod(items.size)
            val item = items[itemIndex]
            BannerCard(item = item)
        }

        Row(
            Modifier
                .height(height = 60.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(times = items.size) { iteration ->
                val currentItemIndex = (pagerState.currentPage - initialPage).mod(items.size)
                val color = if (currentItemIndex == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(all = 2.dp)
                        .clip(shape = CircleShape)
                        .background(color = color)
                        .size(size = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun BannerCard(item: BannerItem) {
    Card(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalPlatformContext.current)
                    .data(data = BaseUrl.IMAGE + item.posterPath)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = rememberVectorPainter(image = Icons.Default.Image),
                error = rememberVectorPainter(image = Icons.Default.BrokenImage)
            )
            Text(
                text = item.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}