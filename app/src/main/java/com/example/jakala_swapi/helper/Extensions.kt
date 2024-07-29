package com.example.jakala_swapi.helper

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.bottomReached() =
    (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 1) >= layoutInfo.totalItemsCount - 1