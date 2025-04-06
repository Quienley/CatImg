package com.catimg.domain.tools.filters.edgedetection

import com.catimg.domain.model.FilterParameter
import com.catimg.domain.model.ToolFilter
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLaplacianFilter

class Laplacian: ToolFilter {
    override val filter = GPUImageLaplacianFilter()
    override val parameters: List<FilterParameter> = emptyList()

    override fun setParameter(index: Int, value: Float) { }
}