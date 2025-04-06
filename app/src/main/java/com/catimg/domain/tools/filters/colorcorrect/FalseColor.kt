package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFalseColorFilter

class FalseColor: ToolFilter {
    override var filter = GPUImageFalseColorFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "First Red value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "First Green value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "First Blue value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Second Red value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Second Green value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Second Blue value",
            minValue = -10f,
            maxValue = 10f,
            currentValue = mutableFloatStateOf(0f)
        ),
    )

    override fun setParameter(index: Int, value: Float) {
        val list: MutableList<Float> = mutableListOf()
        for (i in parameters.indices) {
            if (i == index) {
                list.add(i, value)
            } else {
                list.add(i, parameters[i].currentValue.floatValue)
            }
        }

        filter = GPUImageFalseColorFilter(
            list[0],
            list[1],
            list[2],
            list[3],
            list[4],
            list[5],
        )
    }
}