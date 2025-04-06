package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueFilter

class Hue: ToolFilter {
    override val filter = GPUImageHueFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Hue value",
            minValue = -25f,
            maxValue = 25f,
            currentValue = mutableFloatStateOf(0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setHue(value)
    }
}