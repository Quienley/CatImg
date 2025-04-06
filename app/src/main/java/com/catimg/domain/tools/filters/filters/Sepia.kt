package com.catimg.domain.tools.filters.filters

import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSepiaToneFilter

class Sepia() : ToolFilter {
    override val filter = GPUImageSepiaToneFilter()
    override val parameters: List<FilterParameter> = emptyList()

    override fun setParameter(index: Int, value: Float) { }
}