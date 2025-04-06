package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGammaFilter

class Gamma: ToolFilter {
    override val filter = GPUImageGammaFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Gamma value",
            minValue = 0f,
            maxValue = 3f,
            currentValue = mutableFloatStateOf(1.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setGamma(value)
    }
}