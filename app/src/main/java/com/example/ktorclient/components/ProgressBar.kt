package com.example.ktorclient.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.*
import com.example.ktorclient.R

@Composable
fun CircularProgressBar() {
    val animationSpec by rememberLottieComposition(LottieCompositionSpec.Asset("progress_bar.json"))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = animationSpec,
            iterations = Int.MAX_VALUE
        )

    }

}
@Composable
fun EmptyScreen() {
    val animationSpec by rememberLottieComposition(LottieCompositionSpec.Asset("empty_screen.json"))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = animationSpec,
            iterations = Int.MAX_VALUE
        )

    }

}