package com.gosty.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gosty.common.models.Movie
import com.gosty.common.models.TV

@Composable
fun HorizontalGridSection(
    modifier: Modifier = Modifier,
    title: String,
    exploreText: String,
    onExploreClick: () -> Unit,
    onItemCardClick: (Long) -> Unit,
    tvItems: List<TV> = emptyList(),
    movieItems: List<Movie> = emptyList()
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier.clickable(onClick = onExploreClick),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = exploreText,
                    style = MaterialTheme.typography.labelSmall,
                )

                Spacer(modifier = Modifier.width(width = 4.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            if (tvItems.isNotEmpty()) {
                items(
                    count = tvItems.size,
                    key = { index -> tvItems[index].id }
                ) { index ->
                    val tv = tvItems[index]
                    ItemCard(
                        id = tv.id,
                        title = tv.name,
                        posterPath = tv.posterPath,
                        voteAverage = tv.voteAverage,
                        onClick = { onItemCardClick(tv.id) }
                    )
                }
            }

            if (movieItems.isNotEmpty()) {
                items(
                    count = movieItems.size,
                    key = { index -> movieItems[index].id }
                ) { index ->
                    val movie = movieItems[index]
                    ItemCard(
                        id = movie.id,
                        title = movie.title,
                        posterPath = movie.posterPath,
                        voteAverage = movie.voteAverage,
                        onClick = { onItemCardClick(movie.id) }
                    )
                }
            }
        }
    }
}