package com.catimg.domain.tools.filters.stylization

import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCGAColorspaceFilter

class CGAColorSpace: ToolFilter {
    override val filter = GPUImageCGAColorspaceFilter()
    override val parameters: List<FilterParameter> = emptyList()

    override fun setParameter(index: Int, value: Float) { }
}