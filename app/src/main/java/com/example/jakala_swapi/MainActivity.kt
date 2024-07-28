package com.example.jakala_swapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jakala_swapi.ui.MainViewModel
import com.example.jakala_swapi.ui.theme.JakalaswapiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JakalaswapiTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (val s = uiState) {
                        MainViewModel.MoviesUiState.Error -> {
                            Text(text = "Error")
                        }
                        MainViewModel.MoviesUiState.Loading -> {
                            Text(text = "Loading")
                        }
                        is MainViewModel.MoviesUiState.Success -> {
                            LazyColumn {
                                item {
                                    Greeting(
                                        name = "These are your movies",
                                        modifier = Modifier.padding(innerPadding)
                                    )
                                }

                                items(s.movies){
                                    Text(text = it.title)
                                    Text(text = it.director)
                                    Text(text = it.producer)
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JakalaswapiTheme {
        Greeting("Android")
    }
}