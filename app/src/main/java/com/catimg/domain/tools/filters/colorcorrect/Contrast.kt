package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter

class Contrast: ToolFilter {
    override val filter = GPUImageContrastFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Contrast value",
            minValue = 0f,
            maxValue = 3f,
            currentValue = mutableFloatStateOf(1f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setContrast(value)
    }
}