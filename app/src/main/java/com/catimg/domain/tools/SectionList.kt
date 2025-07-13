package com.catimg.android.catimg.domain.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.catimg.R

@Composable
fun getSectionList() =
    listOf(
        stringResource(R.string.section_filters),
        stringResource(R.string.section_color_correct),
        stringResource(R.string.section_stylization),
        stringResource(R.string.section_sketch_filters),
        stringResource(R.string.section_edge_detection),
        stringResource(R.string.section_blur),
        stringResource(R.string.section_deformation),
)