package com.catimg.data.repository

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.FileProvider
import com.catimg.domain.model.BitmapLruCache
import com.catimg.features.mainmenu.viewmodel.PHOTO_MAX_HEIGHT
import com.catimg.features.mainmenu.viewmodel.PHOTO_MAX_WIDTH
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PhotoRepository {
    private var indexKey = 0
    private var currentPosition = 0

    var isFirstPosition = mutableStateOf(true)
    var isLastPosition = mutableStateOf(true)

    private var bitmapLruCache: BitmapLruCache

    init {
        val maxSize = PHOTO_MAX_WIDTH * PHOTO_MAX_HEIGHT * 4 * 6
        bitmapLruCache = BitmapLruCache(maxSize)
    }

    fun addToCache(photoBitmap: MutableState<Bitmap?>) {
        photoBitmap.value?.let {
            bitmapLruCache.put(indexKey, it)
            currentPosition = indexKey
            indexKey++
            updateBoundStatus()
        }
    }

    fun back(photoBitmap: MutableState<Bitmap?>) {
        if (currentPosition > 0) {
            --currentPosition
            photoBitmap.value = bitmapLruCache[currentPosition]!!
            updateBoundStatus()
        }
    }

    fun forward(photoBitmap: MutableState<Bitmap?>) {
        if (currentPosition < indexKey - 1) {
            ++currentPosition
            photoBitmap.value = bitmapLruCache[currentPosition]!!
            updateBoundStatus()
        }
    }

    private fun updateBoundStatus() {
        isFirstPosition.value = currentPosition == 0
        isLastPosition.value = currentPosition == indexKey - 1
    }

    fun downloadToCache(photoBitmap: MutableState<Bitmap?>, uri: Uri, context: Context) {
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            BitmapFactory.decodeStream(inputStream, null, options)
        }

        val width = options.outWidth
        val height = options.outHeight
        var scaleFactor = 1
        while (width / scaleFactor > PHOTO_MAX_WIDTH || height / scaleFactor > PHOTO_MAX_HEIGHT) {
            scaleFactor *= 2
        }

        val scaledOptions = BitmapFactory.Options().apply { inSampleSize = scaleFactor }
        val bitmap = context
            .contentResolver
            .openInputStream(uri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream, null, scaledOptions)
            }

        if (bitmap != null) {
            photoBitmap.value = bitmap
            addToCache(photoBitmap)
        }
    }

    fun shareImage(context: Context) {
        val file = File(context.cacheDir, "shared_image.png")
        val imageUri = try {
            FileOutputStream(file).use { outputStream ->
                bitmapLruCache[currentPosition]?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Отправить через"))
    }

    fun createImageFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_CATIMG_TEMP_FILE_${indexKey}",
            ".jpg",
            storageDir
        )
    }

    fun saveBitmapToGallery(context: Context,
                            fileName: String,
                            isSavingCurrentPhoto: Boolean): Boolean {
        val index = if (isSavingCurrentPhoto) currentPosition
        else indexKey - 1

        bitmapLruCache[index]?.let { bitmap ->
            val contentResolver = context.contentResolver
            val imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.WIDTH, bitmap.width)
                put(MediaStore.Images.Media.HEIGHT, bitmap.height)
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CatImg")
            }

            try {
                val uri = contentResolver.insert(imageCollection, contentValues)
                uri?.let { outputStreamUri ->
                    contentResolver.openOutputStream(outputStreamUri).use { outputStream ->
                        if (outputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        } else {
                        }
                    }
                }
            } catch (e: IOException) {
                Log.e("SaveBitmap", "Error saving image: ${e.message}")
                return false
            }
        }
        return true
    }

    fun getCurrent() = bitmapLruCache[currentPosition]

    fun reset() {
        bitmapLruCache.evictAll()
        currentPosition = 0
        indexKey = 0
        updateBoundStatus()
    }

    fun rotateImage(photoBitmap: MutableState<Bitmap?>, degree: Float) {
        val matrix = Matrix().apply { postRotate(degree) }
        photoBitmap.value?.let {
            photoBitmap.value = Bitmap.createBitmap(it, 0, 0, it.width, it.height, matrix, true)
            bitmapLruCache.remove(currentPosition)
        }
        photoBitmap.value?.let {
            bitmapLruCache.put(currentPosition, it)
            updateBoundStatus()
        }
    }

    fun flipImage(photoBitmap: MutableState<Bitmap?>, horizontal: Boolean) {
        val matrix = Matrix().apply {
            val scale = if (horizontal) 1f else -1f
            preScale(-scale, scale)
        }
        photoBitmap.value?.let {
            photoBitmap.value = Bitmap.createBitmap(it, 0, 0, it.width, it.height, matrix, true)
            bitmapLruCache.remove(currentPosition)
        }
        photoBitmap.value?.let {
            bitmapLruCache.put(currentPosition, it)
            updateBoundStatus()
        }
    }

    fun applyFilter(photoBitmap: MutableState<Bitmap?>,
                    context: Context,
                    filter: GPUImageFilter,
                    image: Bitmap?) {
        val gpuImage = GPUImage(context)
        gpuImage.setImage(image)
        gpuImage.setFilter(filter)
        photoBitmap.value = gpuImage.bitmapWithFilterApplied
    }

    fun applyFilter(photoBitmap: MutableState<Bitmap?>,
                    context: Context,
                    filter: GPUImageFilter) {
        val gpuImage = GPUImage(context)
        gpuImage.setImage(bitmapLruCache[currentPosition])
        gpuImage.setFilter(filter)
        photoBitmap.value = gpuImage.bitmapWithFilterApplied
    }
}