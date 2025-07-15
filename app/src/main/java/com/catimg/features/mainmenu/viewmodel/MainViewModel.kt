package com.catimg.features.mainmenu.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.catimg.data.repository.PhotoRepository
import com.catimg.domain.model.userPreferencesStore
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.File

const val PHOTO_MAX_WIDTH = 2048
const val PHOTO_MAX_HEIGHT = 2048

class MainViewModel(context: Context): ViewModel() {
    val isFirstPosition
        get() = photoRepository.isFirstPosition

    val isLastPosition
        get() = photoRepository.isLastPosition

    var darkTheme = mutableStateOf(true)

    var photoBitmap = mutableStateOf<Bitmap?>(null)

    private val photoRepository = PhotoRepository()

    lateinit var navController: NavHostController
    private var _screenState = mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            context.userPreferencesStore.data
                .map { it.darkTheme }
                .collect { value ->
                    darkTheme.value = value
                    savePreferenceDarkTheme(context)
                }
        }
    }

    val screenState: Int
        get() = _screenState.intValue

    fun addToCache() {
        if (photoBitmap.value != null) {
            photoRepository.addToCache(photoBitmap)
        }
    }

    fun inverseAppTheme(context: Context) {
        val newValue = !darkTheme.value
        darkTheme.value = newValue
        viewModelScope.launch {
            savePreferenceDarkTheme(context)
        }
    }

    fun decrementScreenStatus() = --_screenState.intValue
    fun incrementScreenStatus() = ++_screenState.intValue

    fun back() = photoRepository.back(photoBitmap)
    fun forward() = photoRepository.forward(photoBitmap)

    fun downloadToCache(uri: Uri, context: Context) =
        photoRepository.downloadToCache(photoBitmap, uri, context)
    fun shareImage(context: Context) = photoRepository.shareImage(context)

    fun refresh() {
        photoBitmap.value = photoRepository.getCurrent()
    }

    fun reset() = photoRepository.reset()

    fun rotateImage(degree: Float) = photoRepository.rotateImage(photoBitmap, degree)
    fun flipImage(horizontal: Boolean) = photoRepository.flipImage(photoBitmap, horizontal)

    fun applyFilter(context: Context,
                    filter: GPUImageFilter,
                    image: Bitmap?) = photoRepository.applyFilter(photoBitmap, context, filter, image)

    fun applyFilter(context: Context,
                    filter: GPUImageFilter) = photoRepository.applyFilter(photoBitmap, context, filter)


    fun createImageFile(context: Context): File {
        return photoRepository.createImageFile(context)
    }

    fun saveBitmapToGallery(context: Context,
                            fileName: String,
                            isSavingCurrentPhoto: Boolean): Boolean {
        return photoRepository.saveBitmapToGallery(context, fileName, isSavingCurrentPhoto)
    }

    suspend fun savePreferenceDarkTheme(context: Context) {
        context.userPreferencesStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setDarkTheme(darkTheme.value)
                .build()
        }
    }
}