package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBFilter

class RGB: ToolFilter {
    override val filter = GPUImageRGBFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Red value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Green value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Blue value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
    )

    override fun setParameter(index: Int, value: Float) {
        when(index) {
            0 -> filter.setRed(value)
            1 -> filter.setGreen(value)
            2 -> filter.setBlue(value)
        }
    }
}