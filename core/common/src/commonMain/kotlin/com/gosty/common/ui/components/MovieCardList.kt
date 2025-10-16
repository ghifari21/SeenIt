package com.gosty.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.gosty.common.constant.BaseUrl
import com.gosty.common.models.Movie
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieCardList(
    modifier: Modifier = Modifier,
    movie: Movie,
    onItemClick: (Long) -> Unit,
    onWatchlistClick: (Movie) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .clickable(onClick = { onItemClick(movie.id) }),
        shape = RoundedCornerShape(size = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Transparent))
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalPlatformContext.current)
                        .data(data = BaseUrl.IMAGE + movie.posterPath)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = movie.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width = 75.dp)
                        .aspectRatio(ratio = 2f / 3f)
                        .clip(shape = RoundedCornerShape(size = 8.dp)),
                    placeholder = rememberVectorPainter(image = Icons.Default.Image),
                    error = rememberVectorPainter(image = Icons.Default.BrokenImage)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 8.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(all = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onWatchlistClick(movie) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Add to Watchlist",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun MovieCardListPreview() {
    MovieCardList(
        movie = Movie(
            id = 1L,
            title = "Movie Title",
            posterPath = "/bKxiLRPVWe2nZXCzt6JPr5HNWYm.jpg",
            backdropPath = "/path/to/backdrop.jpg",
            overview = "This is a brief overview of the movie.",
            releaseDate = "2023-10-01",
            voteAverage = 8.5,
            adult = false,
            genreIDS = listOf(1, 2, 3),
            originalLanguage = "en",
            originalTitle = "title",
            popularity = 23.0,
            video = true,
            voteCount = 100,
        ),
        onItemClick = {},
        onWatchlistClick = {}
    )
}