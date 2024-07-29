package com.example.jakala_swapi.helper

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState

fun LazyListState.bottomReached() =
    (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 1) >= layoutInfo.totalItemsCount - 1

fun MutableState<Boolean>.setBottomBar(isVisible: Boolean){
    this.value = isVisible
}