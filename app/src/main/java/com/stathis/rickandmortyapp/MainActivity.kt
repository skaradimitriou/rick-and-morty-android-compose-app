package com.stathis.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.rickandmortyapp.navigation.MainAppNavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RickAndMortyAppTheme {
                MainAppNavGraph()
            }
        }
    }
}
