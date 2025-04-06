package com.catimg.domain.tools.filters.stylization

import android.graphics.PointF
import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageVignetteFilter

class Vignette() : ToolFilter {
    override val filter = GPUImageVignetteFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Vignette start value",
            minValue = -2f,
            maxValue = 2f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Vignette end value",
            minValue = -2f,
            maxValue = 2f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Center X point",
            minValue = -1f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        ),
        FilterParameter(
            name = "Center Y point",
            minValue = -1f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setVignetteStart(value)
            1 -> filter.setVignetteEnd(value)
            2 -> filter.setVignetteCenter(PointF(value, parameters[3].currentValue.floatValue))
            3 -> filter.setVignetteCenter(PointF(parameters[2].currentValue.floatValue, value))
        }
    }
}