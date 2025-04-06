package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter

class WhiteBalance: ToolFilter {
    override val filter = GPUImageWhiteBalanceFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Tint",
            minValue = -1250f,
            maxValue = 1250f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Temperature",
            minValue = -100000f,
            maxValue = 100000f,
            currentValue = mutableFloatStateOf(0f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setTint(value)
            1 -> filter.setTemperature(value)
        }
    }
}