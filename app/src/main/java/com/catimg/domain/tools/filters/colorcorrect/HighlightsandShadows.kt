package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHighlightShadowFilter

class HighlightsAndShadows: ToolFilter {
    override val filter = GPUImageHighlightShadowFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Shadows value",
            minValue = -25f,
            maxValue = 25f,
            currentValue = mutableFloatStateOf(2f)
        ),
        FilterParameter(
            name = "Highlights value",
            minValue = -25f,
            maxValue = 25f,
            currentValue = mutableFloatStateOf(0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setShadows(value)
            1 -> filter.setHighlights(value)
        }
    }
}