package com.catimg.domain.tools.filters.blur

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBilateralBlurFilter

class BilateralBlur: ToolFilter {
    override val filter = GPUImageBilateralBlurFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Distance normalization",
            minValue = 0f,
            maxValue = 25f,
            currentValue = mutableFloatStateOf(1f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setDistanceNormalizationFactor(value)
    }
}