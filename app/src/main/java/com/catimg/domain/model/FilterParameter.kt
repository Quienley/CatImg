package com.catimg.domain.model

import androidx.compose.runtime.MutableFloatState

data class FilterParameter(
    val name: String,
    val minValue: Float,
    val maxValue: Float,
    var currentValue: MutableFloatState
) {
    fun decodeToUI(): Int {
        return ((currentValue.floatValue - minValue) / (maxValue - minValue) * 100f).toInt()
    }
}