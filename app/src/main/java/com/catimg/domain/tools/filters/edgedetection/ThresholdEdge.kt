package com.catimg.domain.tools.filters.edgedetection

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelThresholdFilter

class ThresholdEdge : ToolFilter {
    override val filter = GPUImageSobelThresholdFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Threshold",
            minValue = 0.8f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.9f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setThreshold(value)
    }
}