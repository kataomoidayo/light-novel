package com.putu.lightnovel.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putu.lightnovel.R
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.ui.common.UiState
import com.putu.lightnovel.ui.component.LightNovelItem
import com.putu.lightnovel.ui.component.SearchBar
import com.putu.lightnovel.ui.theme.LightNovelTheme

@Composable
fun HomeScreen (
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<HomeViewModel>()

    val searchState by viewModel.searchState

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

            }

            is UiState.Success -> {
                HomeContent(
                    lightNovel = uiState.data,
                    navController = navController,
                    query = searchState.query,
                    onQueryChange = viewModel::onQueryChange,
                    modifier = modifier
                )
            }

            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun HomeContent (
    lightNovel: List<LightNovelEntity>,
    navController: NavController,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
        )
        when (lightNovel.isEmpty()) {
            true -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = stringResource(R.string.no_search_data))
            }

            false -> LazyColumn(
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier
            ) {
                items(lightNovel) { data ->
                    LightNovelItem(data, navController, modifier)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailLightNovelPreview() {
    LightNovelTheme {
        HomeScreen(
            navController = rememberNavController(),
            modifier = Modifier
        )
    }
}