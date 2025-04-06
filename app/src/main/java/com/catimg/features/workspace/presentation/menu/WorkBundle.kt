package com.catimg.features.workspace.presentation.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.catimg.android.catimg.domain.tools.getSectionList
import com.catimg.android.catimg.domain.tools.getToolList
import com.catimg.features.workspace.presentation.settings.SettingsWindow
import com.catimg.features.workspace.presentation.menu.components.sectionpanel.presentation.SectionPanel
import com.catimg.features.workspace.presentation.menu.components.ToolsPanel
import com.catimg.features.workspace.viewmodel.WorkSpaceViewModel
import com.catimg.domain.model.ToolFilter
import com.catimg.domain.model.ToolWithFilter
import com.catimg.features.mainmenu.viewmodel.MainViewModel

private const val LINE_WIDTH = 1

@Composable
fun WorkBundle(modifier: Modifier,
               mainViewModel: MainViewModel,
               primaryViewModel: WorkSpaceViewModel, ) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground

    AnimatedContent(
        modifier = Modifier.background(backgroundColor),
        targetState = primaryViewModel.isAdjusting,
        label = "Workspace slides.",
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(800)
            ) togetherWith
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Down,
                animationSpec = tween(800)
            )
        }) { adjusting ->
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxWidth()
                .pointerInput(Unit) {}
                .then(modifier)) {

            if (adjusting) {
                val filter: ToolFilter =
                    (primaryViewModel.selectedTool as ToolWithFilter).toolFilter
                SettingsWindow(
                    filter = filter,
                    mainViewModel = mainViewModel,
                    primaryViewModel = primaryViewModel
                )

            } else {
                AnimatedContent(
                    targetState = primaryViewModel.selectedSection,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(500)) togetherWith
                                fadeOut(animationSpec = tween(500))
                    },
                    label = "Animated fade between tool sections."
                ) { section ->
                    val toolList = getToolList(selectedSection = section)
                    ToolsPanel(
                        toolList = toolList,
                        workSpaceViewModel = primaryViewModel,
                        mainViewModel = mainViewModel
                    )
                }

                Box(modifier = Modifier.background(onBackgroundColor)
                    .fillMaxWidth().height(LINE_WIDTH.dp))

                SectionPanel(sections = getSectionList(),
                    workSpaceViewModel = primaryViewModel)
            }
        }
    }
}