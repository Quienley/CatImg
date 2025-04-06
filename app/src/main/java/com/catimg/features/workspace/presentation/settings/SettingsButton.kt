package com.catimg.features.workspace.presentation.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val PADDING = 16
private const val ROUND_CORNER = 30
private const val TEXT_HEIGHT = 35
private const val TEXT_WIDTH = 30
private const val FONT_SIZE = 15
private const val BORDER_SIZE = 1

@Composable
fun SettingsButton(text: String,
                   modifier: Modifier = Modifier,
                   action: () -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background

    val isClicked = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val backgroundButton by animateColorAsState(
        targetValue = if (isClicked.value) onBackgroundColor
        else backgroundColor,
        label = "Background Color Animation"
    )

    val textColor by animateColorAsState(
        targetValue = if (isClicked.value) backgroundColor
        else onBackgroundColor,
        label = "Text Color Animation"
    )

    Text(
        text = text,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        fontSize = FONT_SIZE.sp,
        modifier = Modifier
            .padding(vertical = (PADDING / 2).dp, horizontal = PADDING.dp)
            .height(TEXT_HEIGHT.dp)
            .border(BORDER_SIZE.dp, onBackgroundColor, RoundedCornerShape(ROUND_CORNER.dp))
            .clip(shape = RoundedCornerShape(ROUND_CORNER.dp))
            .background(backgroundButton)
            .clickable {
                if (!isClicked.value) {
                    action()
                    coroutineScope.launch {
                        delay(450)
                        isClicked.value = false
                    }
                }
            }
            .then(modifier)
            .padding(horizontal = (TEXT_WIDTH).dp, vertical = (PADDING / 4).dp),
        color = textColor,
    )
}