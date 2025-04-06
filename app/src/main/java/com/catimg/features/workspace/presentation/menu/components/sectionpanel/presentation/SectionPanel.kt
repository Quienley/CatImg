package com.catimg.features.workspace.presentation.menu.components.sectionpanel.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catimg.features.workspace.presentation.menu.components.sectionpanel.viewmodel.SectionPanelViewModel
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import kotlinx.coroutines.launch

private const val PADDING = 16
private const val ROUND_CORNER = 30
private const val LAZY_ROW_VERTICAL_PADDING = 8
private const val TEXT_HEIGHT = 35
private const val TEXT_WIDTH = 30
private const val FONT_SIZE = 15
private const val BORDER_SIZE = 1

@Composable
fun SectionPanel(
    sections: List<String>,
    workSpaceViewModel: WorkSpaceViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: SectionPanelViewModel = viewModel()
    val width = LocalConfiguration.current.screenWidthDp

    LazyRow(
        state = viewModel.listState,
        modifier = Modifier.fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(vertical = LAZY_ROW_VERTICAL_PADDING.dp)
    ) {
        coroutineScope.launch() {
            viewModel.listState.animateScrollToItem(
                index = viewModel.currentSectionIndex.intValue,
                scrollOffset = -(width + TEXT_WIDTH + PADDING * 2) / 2
            )
        }

        itemsIndexed(sections) { index, section ->
            if (workSpaceViewModel.selectedSection == section)
                viewModel.currentSectionIndex.intValue = index

            val onBackgroundColor = MaterialTheme.colorScheme.onBackground
            val backgroundColor = MaterialTheme.colorScheme.background

            val backgroundButton by animateColorAsState(
                targetValue = if (workSpaceViewModel.selectedSection == section) onBackgroundColor
                            else backgroundColor,
                label = "Background Color Animation"
            )

            val textColor by animateColorAsState(
                targetValue = if (workSpaceViewModel.selectedSection == section) backgroundColor
                            else onBackgroundColor,
                label = "Text Color Animation"
            )

            Text(
                text = section,
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
                        if (!workSpaceViewModel.isAdjusting) {
                            coroutineScope.launch {
                                viewModel.listState.animateScrollToItem(
                                    viewModel.currentSectionIndex.intValue,
                                    -(width + TEXT_WIDTH) / 2
                                )
                            }
                            workSpaceViewModel.selectedSection = section
                        }
                    }
                    .padding(horizontal = (TEXT_WIDTH).dp, vertical = (PADDING / 4).dp),
                color = textColor,
            )
        }
    }
}