package com.catimg.domain.tools.filters.distortion

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDilationFilter

class Dilation: ToolFilter {
    override var filter = GPUImageDilationFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Radius value",
            minValue = 1f,
            maxValue = 4f,
            currentValue = mutableFloatStateOf(2f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter = GPUImageDilationFilter(value.toInt())
    }
}