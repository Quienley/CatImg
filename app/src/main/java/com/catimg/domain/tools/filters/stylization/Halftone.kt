package com.catimg.domain.tools.filters.stylization

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHalftoneFilter

class Halftone: ToolFilter {
    override val filter = GPUImageHalftoneFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Fractional width of a pixel",
            minValue = 0.005f,
            maxValue = 0.05f,
            currentValue = mutableFloatStateOf(0.025f)
        ),
        FilterParameter(
            name = "Aspect ratio",
            minValue = 0f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        ),
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setFractionalWidthOfAPixel(value)
            1 -> filter.setAspectRatio(value)
        }
    }
}