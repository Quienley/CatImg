package com.catimg.domain.tools.filters.sketch

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter

class Kuwahara() : ToolFilter {
    override val filter = GPUImageKuwaharaFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Radius brush-stroke",
            minValue = 1f,
            maxValue = 12f,
            currentValue = mutableFloatStateOf(3f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setRadius(value.toInt())
    }
}