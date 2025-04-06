package com.catimg.domain.tools.filters.distortion

import android.graphics.PointF
import androidx.compose.runtime.mutableFloatStateOf
import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSphereRefractionFilter

class SphereRefraction: ToolFilter {
    override val filter = GPUImageSphereRefractionFilter()
    override val parameters = listOf(
        FilterParameter(
            name = "Radius",
            minValue = 0.0f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        ),
        FilterParameter(
            name = "Refractive index",
            minValue = -2f,
            maxValue = 2f,
            currentValue = mutableFloatStateOf(0f)
        ),
        FilterParameter(
            name = "Center X point",
            minValue = -1f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        ),
        FilterParameter(
            name = "Center Y point",
            minValue = -1f,
            maxValue = 1f,
            currentValue = mutableFloatStateOf(0.5f)
        )
    )

    override fun setParameter(index: Int, value: Float) {
        when (index) {
            0 -> filter.setRadius(value)
            1 -> filter.setRefractiveIndex(value)
            2 -> filter.setCenter(PointF(value, parameters[3].currentValue.floatValue))
            3 -> filter.setCenter(PointF(parameters[2].currentValue.floatValue, value))
        }
    }
}