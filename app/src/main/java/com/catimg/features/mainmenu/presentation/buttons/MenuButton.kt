package com.catimg.features.mainmenu.presentation.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private const val ICON_SIZE = 112
private const val BUTTON_WIDTH = 216
private const val CONTENT_PADDING = 8
private const val BUTTON_SHAPE_RADIUS = 8
private const val BUTTON_BORDER_WIDTH = 1
private const val FONT_SIZE = 16f

@Composable
fun MenuButton(label: String,
               iconPainter: Painter,
               onClickAction: () -> Unit) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val shapeButton = RoundedCornerShape(BUTTON_SHAPE_RADIUS.dp)

    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            delay(250)
            isPressed = false
        }
    }

    val borderButtonColor by animateColorAsState(
        targetValue = if (isPressed) backgroundColor else onBackgroundColor,
        label = "Border Color Animation"
    )

    val backgroundButton by animateColorAsState(
        targetValue = if (isPressed) onBackgroundColor else backgroundColor,
        label = "Background Color Animation"
    )

    val textColor by animateColorAsState(
        targetValue = if (isPressed) backgroundColor else onBackgroundColor,
        label = "Text Color Animation"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isPressed) backgroundColor else onBackgroundColor,
        label = "Icon Color Animation"
    )

    val paddingSize by animateDpAsState(
        targetValue = if (isPressed) (CONTENT_PADDING / 2).dp else CONTENT_PADDING.dp,
        label = "Padding Animation",
    )

    val iconSize by animateDpAsState(
        targetValue = if (isPressed) (ICON_SIZE * 1.2).dp else ICON_SIZE.dp,
        label = "Icon Size Animation",
    )

    val fontSize by animateFloatAsState(
        targetValue = if (isPressed) FONT_SIZE * 1.15f else FONT_SIZE,
        label = "Font Size Animation"
    )

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(BUTTON_WIDTH.dp)
            .border(width = BUTTON_BORDER_WIDTH.dp,
                color = borderButtonColor,
                shape = shapeButton)
            .background(backgroundButton, shapeButton)
            .clickable {
                isPressed = true
                onClickAction()
            },

        ) {
        Icon(
            painter = iconPainter,
            contentDescription = label,
            modifier = Modifier
                .size(iconSize)
                .padding(paddingSize),
            tint = iconColor)
        Text(
            text = label,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize.sp,
            modifier = Modifier
                .padding(paddingSize)
                .offset(x = 0.dp, y = -CONTENT_PADDING.dp)
        )
    }
}