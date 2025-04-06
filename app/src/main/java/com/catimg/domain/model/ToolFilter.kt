package com.catimg.domain.model

import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter

interface ToolFilter {
    val filter: GPUImageFilter
    val parameters: List<FilterParameter>

    fun setParameter(index: Int, value: Float)
}