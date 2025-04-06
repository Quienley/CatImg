package com.catimg.domain.tools.filters.edgedetection

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter

class SobelEdgeDetection() : ToolFilter {
    override val filter = GPUImageSobelEdgeDetectionFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Line size",
            minValue = 0.1f,
            maxValue = 3f,
            currentValue = mutableFloatStateOf(1.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setLineSize(value)
    }
}