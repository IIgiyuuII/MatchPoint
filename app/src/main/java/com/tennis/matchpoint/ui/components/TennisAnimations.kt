package com.tennis.matchpoint.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tennis.matchpoint.ui.theme.BallYellow
import kotlinx.coroutines.delay

/**
 * Анимация "начало матча": две ракетки съезжаются с краёв экрана и встречаются в центре
 * (символическое "столкновение"), затем мячик выпрыгивает наверх. Чистый Compose,
 * без внешних ассетов — компактно и легко переносится в другие экраны.
 */
@Composable
fun MatchStartAnimation(
    player1Name: String,
    player2Name: String,
    onFinished: () -> Unit
) {
    var play by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        play = true
        delay(1600)
        onFinished()
    }

    val progress by animateFloatAsState(
        targetValue = if (play) 1f else 0f,
        animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing),
        label = "racketsClash"
    )
    val bounce by rememberInfiniteTransition(label = "ball").let { transition ->
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(tween(500, easing = LinearEasing), RepeatMode.Reverse),
            label = "ballBounce"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.height(140.dp)) {
                // Ракетка слева
                Icon(
                    imageVector = Icons.Filled.SportsTennis,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(72.dp)
                        .offset(x = (-90 * (1 - progress)).dp)
                        .rotate(-25f + 25f * progress)
                )
                // Ракетка справа (зеркально)
                Icon(
                    imageVector = Icons.Filled.SportsTennis,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(72.dp)
                        .offset(x = (90 * (1 - progress)).dp)
                        .rotate(180f + 25f - 25f * progress)
                )
                // Мячик выпрыгивает, когда ракетки сошлись
                if (progress > 0.85f) {
                    Box(
                        modifier = Modifier
                            .offset(y = (-24 * bounce).dp)
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(BallYellow)
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "$player1Name  vs  $player2Name",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(text = "Матч начинается", color = Color.White.copy(alpha = 0.85f))
        }
    }
}

/**
 * Анимация победы: кубок выезжает и увеличивается, имя победителя проявляется,
 * на фоне разлетаются "конфетти" из мячиков.
 */
@Composable
fun MatchWinnerAnimation(
    winnerName: String,
    onDismiss: () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "trophyScale"
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(600, delayMillis = 300),
        label = "textAlpha"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.75f)),
        contentAlignment = Alignment.Center
    ) {
        ConfettiBalls()
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.EmojiEvents,
                contentDescription = null,
                tint = BallYellow,
                modifier = Modifier
                    .size(96.dp)
                    .scale(scale)
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "$winnerName победил!",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )
            Spacer(Modifier.height(24.dp))
            androidx.compose.material3.Button(onClick = onDismiss) {
                Text("Готово")
            }
        }
    }
}

@Composable
private fun ConfettiBalls() {
    val infinite = rememberInfiniteTransition(label = "confetti")
    val fall by infinite.animateFloat(
        initialValue = -1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(tween(2200, easing = LinearEasing), RepeatMode.Restart),
        label = "confettiFall"
    )
    val positions = remember { List(10) { (it * 37 % 100) / 100f } }
    Box(modifier = Modifier.fillMaxSize()) {
        positions.forEachIndexed { i, xFraction ->
            val delayFactor = (i % 5) * 0.15f
            val yProgress = ((fall + delayFactor) % 2f) - 1f
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(
                            x = (xFraction * 320).dp,
                            y = (yProgress * 500 + 500).dp
                        )
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(BallYellow.copy(alpha = 0.9f))
                )
            }
        }
    }
}
