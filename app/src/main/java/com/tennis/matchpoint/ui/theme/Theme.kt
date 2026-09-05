package com.tennis.matchpoint.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightScheme = lightColorScheme(
    primary = CourtGreen,
    onPrimary = LineWhite,
    secondary = BallYellow,
    onSecondary = Charcoal,
    tertiary = ClayOrange,
    background = SoftGray,
    surface = LineWhite,
    error = WarnRed
)

private val DarkScheme = darkColorScheme(
    primary = CourtGreenLight,
    onPrimary = Charcoal,
    secondary = BallYellow,
    onSecondary = Charcoal,
    tertiary = ClayOrange,
    background = Charcoal,
    surface = CourtGreenDark,
    error = WarnRed
)

@Composable
fun MatchPointTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkScheme else LightScheme
    MaterialTheme(
        colorScheme = colors,
        typography = MatchPointTypography,
        content = content
    )
}
