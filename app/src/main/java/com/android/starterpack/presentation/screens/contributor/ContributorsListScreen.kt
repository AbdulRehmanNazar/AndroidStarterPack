package com.android.starterpack.presentation.screens.contributor

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.android.starterpack.R
import com.android.starterpack.core.presentation.common.LoadingDialog
import com.android.starterpack.core.presentation.state.UiState
import com.android.starterpack.core.util.showToast
import com.android.starterpack.domain.model.Contributor
import org.koin.compose.koinInject

/**
 * Voyager navigation with screen to show list of contributors
 */
class ContributorsListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ContributorsViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(Unit) { viewModel.getContributors() }

        when (val uiState = state) {
            is UiState.Idle -> {}
            is UiState.Loading -> {
                LoadingDialog(true)
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(uiState.data) {
                        ContributorItem(it)
                    }
                }
            }

            is UiState.Error -> {
                Text("Error: ${uiState.message}")
            }
        }
    }
}

@Composable
fun ContributorItem(contributor: Contributor) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            context.showToast("${contributor.login} clicked")
        }) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(contributor.avatarUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Image with options",
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier.size(50.dp),
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(contributor.login!!)
            Text("Contributions: ${contributor.contributions}")
        }
    }
}