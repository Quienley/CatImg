package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter

class Sharpen: ToolFilter {
    override val filter = GPUImageSharpenFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Sharpness value",
            minValue = -50f,
            maxValue = 50f,
            currentValue = mutableFloatStateOf(-0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setSharpness(value)
    }
}