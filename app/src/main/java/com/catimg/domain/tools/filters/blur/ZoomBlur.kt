package com.catimg.domain.tools.filters.blur

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBoxBlurFilter

class ZoomBlur: ToolFilter {
    override val filter = GPUImageBoxBlurFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Blur size",
            minValue = 0f,
            maxValue = 3f,
            currentValue = mutableFloatStateOf(1f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setBlurSize(value)
    }
}