package com.catimg.domain.tools.filters.colorcorrect

import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLevelsFilter

class Levels: ToolFilter {
    override val filter = GPUImageLevelsFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Red minimal value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Red medium value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Red maximum value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Green minimal value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Green medium value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Green maximum value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Blue minimal value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Blue medium value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Blue maximum value",
            minValue = -100f,
            maxValue = 100f,
            currentValue = mutableFloatStateOf(0f)
        ),
    )

    override fun setParameter(index: Int, value: Float) {
        val listValues = emptyList<Float>().toMutableList()

        when (index) {
            in 0..2 -> {
                for (i in 0..2) {
                    if (i == index) listValues.add(i, value)
                    else listValues.add(i, parameters[i].currentValue.floatValue)
                }
                filter.setRedMin(listValues[0], listValues[1], listValues[2])
            }
            in 3..5 -> {
                for (i in 0..2) {
                    if (i + 3 == index) listValues.add(i, value)
                    else listValues.add(i, parameters[i].currentValue.floatValue)
                }
                filter.setBlueMin(listValues[0], listValues[1], listValues[2])
            }
            in 6..8 -> {
                for (i in 0..2) {
                    if (i + 5 == index) listValues.add(i, value)
                    else listValues.add(i, parameters[i].currentValue.floatValue)
                }
                filter.setGreenMin(listValues[0], listValues[1], listValues[2])
            }
        }
    }
}