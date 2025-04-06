package com.catimg.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

class ToolWithFilter(
    name: String,
    icon: ImageVector,
    val toolFilter: ToolFilter
): Tool(name, icon) {
}