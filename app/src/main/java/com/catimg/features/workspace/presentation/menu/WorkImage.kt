package com.catimg.features.workspace.presentation.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel

@Composable
fun WorkImage(modifier: Modifier,
              mainViewModel: MainViewModel,
              primaryViewModel: WorkSpaceViewModel
) {

    val animatedScale by animateFloatAsState(
        targetValue = primaryViewModel.scale,
        animationSpec = tween(350),
        label = "Scale Animation."
    )

    val animatedOffset by animateOffsetAsState(
        targetValue = primaryViewModel.offset,
        animationSpec = tween(300),
        label = "Offset Animation."
    )

    AnimatedContent(
        modifier = Modifier.then(modifier),
        targetState = mainViewModel.photoBitmap.value,
        transitionSpec = {
            fadeIn(animationSpec = tween(500), initialAlpha = 0.5f) togetherWith
            fadeOut(animationSpec = tween(500), targetAlpha = 0.5f)
        },
        label = "Animated fade between tool sections."
    ) { bitmap ->
        bitmap?.let {
            val modifierPhoto = Modifier.fillMaxSize()
                .offset(y = 50.dp)
                .background(MaterialTheme.colorScheme.background)
                .onSizeChanged { size -> primaryViewModel.imageSize = size }
                .graphicsLayer(
                    scaleX = animatedScale,
                    scaleY = animatedScale,
                    translationX = animatedOffset.x,
                    translationY = animatedOffset.y
                )
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        primaryViewModel.transformImage(pan, zoom)
                    }
                }

            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "User's photo",
                contentScale = ContentScale.Inside,
                modifier = modifierPhoto
            )
        }
    }
}