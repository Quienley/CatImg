package com.catimg.domain.tools.filters.stylization

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePixelationFilter

class Pixelation: ToolFilter {
    override val filter = GPUImagePixelationFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Pixel value",
            minValue = 5f,
            maxValue = 50f,
            currentValue = mutableFloatStateOf(25f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setPixel(value)
    }
}