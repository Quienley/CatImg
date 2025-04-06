package com.catimg.features.workspace.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.catimg.domain.model.Tool

const val MIN_SCALE = 0.9f
const val MAX_SCALE = 50f

class WorkSpaceViewModel(): ViewModel() {
    private var _selectedSection = mutableStateOf("Filters")
    private var _selectedTool = mutableStateOf<Tool?>(null)
    private var _isAdjusting = mutableStateOf(false)
    private var _isDeleting = mutableStateOf(false)
    private var _scale = mutableFloatStateOf(1f)
    private var _offset = mutableStateOf(Offset(0f, 0f))
    private var _imageSize = mutableStateOf(IntSize(0, 0))

    var drawerState = DrawerState(DrawerValue.Closed)

    var selectedSection: String
        get() = _selectedSection.value
        set(value) { _selectedSection.value = value }

    var selectedTool: Tool?
        get() = _selectedTool.value
        set(value) { _selectedTool.value = value }

    var isAdjusting: Boolean
        get() = _isAdjusting.value
        set(value) { _isAdjusting.value = value }

    var isDeleting: Boolean
        get() = _isDeleting.value
        set(value) { _isDeleting.value = value }

    var scale: Float
        get() = _scale.floatValue
        set(value) { _scale.floatValue = value }

    var offset: Offset
        get() = _offset.value
        set(value) { _offset.value = value }

    var imageSize: IntSize
        get() = _imageSize.value
        set(value) { _imageSize.value = value }

    fun transformImage(pan: Offset, zoom: Float) {
        val newScale = (scale * zoom)
            .coerceIn(MIN_SCALE, MAX_SCALE)
        val scaledWidth = imageSize.width * newScale
        val scaledHeight = imageSize.height * newScale

        val newOffset = Offset(
            x = if (scaledWidth > imageSize.width) {
                val minX =
                    -(scaledWidth - imageSize.width) / 2
                val maxX =
                    (scaledWidth - imageSize.width) / 2
                (offset.x + pan.x * newScale).coerceIn(minX, maxX)
            } else 0f,

            y = if (scaledHeight > imageSize.height) {
                val minY =
                    -(scaledHeight - imageSize.height) / 2
                val maxY =
                    (scaledHeight - imageSize.height) / 2
                (offset.y + pan.y * newScale).coerceIn(
                    minY,
                    maxY
                )
            } else 0f
        )

        scale = newScale
        offset = newOffset
    }

    fun inverseAdjusting() {
        _isAdjusting.value = !_isAdjusting.value
    }

    fun inverseDeletingStatus() {
        _isDeleting.value = !_isDeleting.value
    }

    suspend fun openModalMenu() {
        drawerState.open()
    }

    suspend fun closeModalMenu() {
        drawerState.close()
    }
}