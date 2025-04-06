package com.catimg.domain.tools.filters.stylization

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePosterizeFilter

class Posterize: ToolFilter {
    override val filter = GPUImagePosterizeFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Color levels",
            minValue = 0.6f,
            maxValue = 15f,
            currentValue = mutableFloatStateOf(0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        filter.setColorLevels(value.toInt())
    }
}