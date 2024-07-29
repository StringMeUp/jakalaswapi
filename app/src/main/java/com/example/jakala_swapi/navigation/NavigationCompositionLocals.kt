package com.example.jakala_swapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

object NavigationCompositionLocals {
    /** Basically, helper object to extract/(create) a shared viewmodel based on its backstack entry.*/
    val LocalNavController = staticCompositionLocalOf<NavController> {
        error("CompositionLocal LocalNavController not present")
    }

    @Composable
    inline fun <reified T : ViewModel> NavBackStackEntry.viewModelScopedTo(route: String): T {
        val navController = LocalNavController.current
        val parentEntry = remember(this) { navController.getBackStackEntry(route) }
        return hiltViewModel(parentEntry)
    }

    @Composable
    fun NavigationProvider(navController: NavController, content: @Composable () -> Unit) {
        CompositionLocalProvider(
            LocalNavController provides navController,
            content = content
        )
    }
}