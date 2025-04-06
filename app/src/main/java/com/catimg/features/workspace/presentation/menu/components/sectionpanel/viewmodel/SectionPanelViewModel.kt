package com.catimg.features.workspace.presentation.menu.components.sectionpanel.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class SectionPanelViewModel: ViewModel() {
    var currentSectionIndex = mutableIntStateOf(0)
    val listState = LazyListState()
}