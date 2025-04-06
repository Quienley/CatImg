package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter

class Brightness: ToolFilter {
    override val filter = GPUImageBrightnessFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Brightness value",
            minValue = 0f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setBrightness(value)
    }
}