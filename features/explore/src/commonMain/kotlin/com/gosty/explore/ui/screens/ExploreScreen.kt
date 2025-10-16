package com.gosty.explore.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.gosty.common.routes.ContentType
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    labels: Map<String, String>,
    onNavigateToDetail: (Long, ContentType) -> Unit,
    onNavigateBack: () -> Unit
) {
    val viewModel = koinViewModel<ExploreViewModel>()
    val state by viewModel.uiState.collectAsState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreContent(
    modifier: Modifier = Modifier,
    labels: Map<String, String>,
    state: ExploreState,
    onNavigateToDetail: (Long, ContentType) -> Unit,
    onNavigateBack: () -> Unit
) {
    val lazyTvItems = state.tvShows?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.title)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        
    }
}

@Preview
@Composable
fun ExploreContentPreview() {
    ExploreContent(
        labels = mapOf(
            "movies" to "Movies",
            "tvShows" to "TV Shows",
            "nowPlaying" to "Now Playing",
            "popular" to "Popular",
            "topRated" to "Top Rated",
            "upcoming" to "Upcoming",
            "airingToday" to "Airing Today",
            "onTheAir" to "On The Air",
            "explore" to "Explore",
            "retry" to "Retry"
        ),
        state = ExploreState(),
        onNavigateToDetail = { _, _ -> },
        onNavigateBack = {}
    )
}