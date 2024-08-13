package com.example.jakala_swapi.ui.screens.people


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jakala_swapi.R
import com.example.jakala_swapi.helper.bottomReached
import com.example.jakala_swapi.helper.preview.PeopleUiStatePreviewProvider
import com.example.jakala_swapi.ui.PeopleUiState
import com.example.jakala_swapi.ui.widgets.ErrorItem
import com.example.jakala_swapi.ui.widgets.HeaderItem
import com.example.jakala_swapi.ui.widgets.LoadingItem

@Composable
fun PeopleScreen(
    viewModel: PeopleViewModel = hiltViewModel(),
    padding: PaddingValues
) {
    val peopleUiState by viewModel.peopleUiStateStateFlow.collectAsStateWithLifecycle()
    Scaffold(Modifier.padding(padding)) {
        PeopleScreenContent(
            padding = it,
            state = peopleUiState,
            loadNextPage = { viewModel.loadNextPage(it) })
    }
}

@Composable
@Preview(showBackground = true)
private fun PeopleScreenContent(
    @PreviewParameter(PeopleUiStatePreviewProvider::class) state: PeopleUiState,
    padding: PaddingValues = PaddingValues(),
    loadNextPage: (page: Int) -> Unit = {}
) {
    val listState = rememberLazyListState()

    when {
        state.isLoading -> LoadingItem()
        state.isError -> ErrorItem()
        else -> {
            LazyColumn(state = listState) {
                item {
                    HeaderItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        title = stringResource(id = R.string.header_item_people)
                    )
                }

                items(state.items) {
                    ListItem(
                        modifier = Modifier
                            .padding(8.dp)
                            .shadow(2.dp, RoundedCornerShape(16.dp)),
                        colors = ListItemDefaults.colors(containerColor = Color.LightGray),

                        leadingContent = {
                            Image(
                                modifier = Modifier.size(100.dp),
                                painter = painterResource(id = R.drawable.ic_people),
                                contentDescription = ""
                            )
                        },
                        headlineContent = {
                            Text(text = "Name: ${it.name ?: "Unknown"}")
                        },
                        supportingContent = {
                            Text(text = "Homeworld: ${it.name ?: "Unknown"}")
                        })
                }
            }
        }
    }

    val isBottomReached by remember {
        derivedStateOf { listState.bottomReached() }
    }

    LaunchedEffect(isBottomReached) {
        if (isBottomReached) loadNextPage(state.page)
    }
}