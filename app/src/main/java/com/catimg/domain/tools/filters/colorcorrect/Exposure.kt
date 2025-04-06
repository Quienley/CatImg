package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExposureFilter

class Exposure: ToolFilter {
    override val filter = GPUImageExposureFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Exposure value",
            minValue = -1f,
            maxValue = 2f,
            currentValue = mutableFloatStateOf(0.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setExposure(value)
    }
}