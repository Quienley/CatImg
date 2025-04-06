package com.catimg.domain.tools.filters.stylization

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageEmbossFilter

class Emboss: ToolFilter {
    override val filter = GPUImageEmbossFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Intensity value",
            minValue = 0f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.intensity = value
    }
}