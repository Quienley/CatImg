package com.catimg.domain.tools.filters.filters

import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter

class ColorInversion: ToolFilter {
    override val filter = GPUImageColorInvertFilter()
    override val parameters: List<FilterParameter> = emptyList()

    override fun setParameter(index: Int, value: Float) { }
}