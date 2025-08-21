package com.gosty.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gosty.common.ui.components.HorizontalGridSection
import com.gosty.home.ui.components.HeroBanner
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    labels: Map<String, String>,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsState()

    when {
        state.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        state.isError -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.errorMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )

                ElevatedButton(
                    onClick = {
                        viewModel.onEvent(HomeEvent.Refresh)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = labels["retry"] ?: "Retry")
                }
            }
        }

        else -> {
            HomeContent(
                modifier = modifier,
                labels = labels,
                state = state
            )
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    labels: Map<String, String>,
    state: HomeState
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(all = 16.dp)
    ) {
        HeroBanner(
            items = state.bannerItems
        )

        Text(
            text = labels["movies"] ?: "Movies",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        HorizontalGridSection(
            title = labels["now_playing"] ?: "Now Playing",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to Movies Screen */ },
            onItemCardClick = { /* Handle Movie Click */ },
            movieItems = state.nowPlayingMovies
        )

        HorizontalGridSection(
            title = labels["popular"] ?: "Popular",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to TV Shows Screen */ },
            onItemCardClick = { /* Handle TV Show Click */ },
            movieItems = state.popularMovies
        )

        HorizontalGridSection(
            title = labels["top_rated"] ?: "Top Rated",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to Movies Screen */ },
            onItemCardClick = { /* Handle Movie Click */ },
            movieItems = state.topRatedMovies
        )

        HorizontalGridSection(
            title = labels["upcoming"] ?: "Upcoming",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to Movies Screen */ },
            onItemCardClick = { /* Handle Movie Click */ },
            movieItems = state.upcomingMovies
        )

        Text(
            text = labels["tvShows"] ?: "TV Shows",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        HorizontalGridSection(
            title = labels["airingToday"] ?: "Airing Today",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to TV Shows Screen */ },
            onItemCardClick = { /* Handle TV Show Click */ },
            tvItems = state.airingTodayTVShows
        )

        HorizontalGridSection(
            title = labels["onTheAir"] ?: "On The Air",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to TV Shows Screen */ },
            onItemCardClick = { /* Handle TV Show Click */ },
            tvItems = state.onTheAirTVShows
        )

        HorizontalGridSection(
            title = labels["popular"] ?: "Popular",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to TV Shows Screen */ },
            onItemCardClick = { /* Handle TV Show Click */ },
            tvItems = state.popularTVShows
        )

        HorizontalGridSection(
            title = labels["topRated"] ?: "Top Rated",
            exploreText = labels["explore"] ?: "Explore",
            onExploreClick = { /* Navigate to TV Shows Screen */ },
            onItemCardClick = { /* Handle TV Show Click */ },
            tvItems = state.topRatedTVShows
        )
    }
}