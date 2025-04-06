package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter

class Saturation: ToolFilter {
    override val filter = GPUImageSaturationFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Saturation",
            minValue = 0f,
            maxValue = 3f,
            currentValue = mutableFloatStateOf(1f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setSaturation(value)
    }
}