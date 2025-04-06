package com.catimg.domain.tools.filters.filters

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHazeFilter

class Haze: ToolFilter {
    override val filter = GPUImageHazeFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Strength of the color applied",
            minValue = -0.3f,
            maxValue = 0.3f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Amount of color change",
            minValue = -0.3f,
            maxValue = 0.3f,
            currentValue = mutableFloatStateOf(0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setDistance(value)
            1 -> filter.setSlope(value)
        }
    }
}