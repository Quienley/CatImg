package com.catimg.features.workspace.presentation.menu.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catimg.features.mainmenu.viewmodel.MainViewModel
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import com.catimg.domain.model.Tool
import kotlinx.coroutines.delay

private const val PADDING_BUTTON = 6
private const val ROUND_CORNER = 6
private const val ICON_SIZE = 35
private const val BUTTON_WIDTH = 96
private const val BUTTON_HEIGHT = 124
private const val SPACE_BETWEEN_BUTTONS = 10
private const val BUTTON_BORDER_WIDTH = 2
private const val BUTTON_VERTICAL_SPACE = 8
private const val FONT_SIZE = 13
private const val LAZY_ROW_VERTICAL_PADDING = 11
private const val TEXT_HEIGHT = 24

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToolsPanel(
    toolList: List<Tool>,
    workSpaceViewModel: WorkSpaceViewModel,
    mainViewModel: MainViewModel
) {
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    var isSelectedTool by remember { mutableStateOf(false) }

    LaunchedEffect(isSelectedTool) {
        if (isSelectedTool) {
            delay(750)
            isSelectedTool = false
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(vertical = LAZY_ROW_VERTICAL_PADDING.dp)
    ) {
        itemsIndexed(toolList) { index, tool ->
            var isPressed by remember { mutableStateOf(false) }

            LaunchedEffect(isPressed) {
                if (isPressed) {
                    delay(250)
                    isPressed = false
                }
            }

            val iconColor by animateColorAsState(
                targetValue = if (isPressed) backgroundColor else onBackgroundColor,
                label = "Icon Color Animation"
            )

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

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(
                        start = if (index == 0) SPACE_BETWEEN_BUTTONS.dp
                                else (SPACE_BETWEEN_BUTTONS / 2).dp,
                        end = if (index == toolList.lastIndex) SPACE_BETWEEN_BUTTONS.dp
                                else (SPACE_BETWEEN_BUTTONS / 2).dp,
                        top = BUTTON_VERTICAL_SPACE.dp,
                        bottom = BUTTON_VERTICAL_SPACE.dp)
                    .width(BUTTON_WIDTH.dp)
                    .height(BUTTON_HEIGHT.dp)
                    .border(
                        width = BUTTON_BORDER_WIDTH.dp,
                        color = borderButtonColor,
                        shape = RoundedCornerShape(ROUND_CORNER))
                    .background(backgroundButton)
                    .combinedClickable(
                        onClickLabel = "Open window settings",
                        onClick = {
                            isPressed = true
                            mainViewModel.incrementScreenStatus()
                            if (!isSelectedTool && !workSpaceViewModel.isAdjusting) {
                                isSelectedTool = true
                                workSpaceViewModel.selectedTool = tool
                                workSpaceViewModel.inverseAdjusting()
                            }
                        },
                        onDoubleClick = { },
                        onLongClick = { })
            ) {
                Icon(
                    imageVector = tool.icon,
                    contentDescription = tool.name,
                    modifier = Modifier.size(ICON_SIZE.dp),
                    tint = iconColor
                )
                Text(
                    text = tool.name,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    fontSize = FONT_SIZE.sp,
                    modifier = Modifier
                        .padding(PADDING_BUTTON.dp)
                        .fillMaxWidth()
                        .height(TEXT_HEIGHT.dp),
                    color = textColor
                )
            }
        }
    }
}