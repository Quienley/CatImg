package com.catimg.domain.tools.filters.sketch

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCrosshatchFilter

class CrossHatch() : ToolFilter {
    override val filter = GPUImageCrosshatchFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Cross hatch spacing",
            minValue = 0.01f,
            maxValue = 0.03f,
            currentValue = mutableFloatStateOf(0.02f)
        ),
        FilterParameter(
            name = "Line width",
            minValue = 0.001f,
            maxValue = 0.003f,
            currentValue = mutableFloatStateOf(0.002f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setCrossHatchSpacing(value)
            1 -> filter.setLineWidth(value)
        }
    }
}