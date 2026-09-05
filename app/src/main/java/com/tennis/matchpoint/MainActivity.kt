package com.tennis.matchpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tennis.matchpoint.ui.navigation.MatchPointNavHost
import com.tennis.matchpoint.ui.theme.MatchPointTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchPointTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MatchPointNavHost()
                }
            }
        }
    }
}
