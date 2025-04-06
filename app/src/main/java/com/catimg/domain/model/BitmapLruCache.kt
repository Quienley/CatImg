package com.catimg.domain.model

import android.graphics.Bitmap
import androidx.collection.LruCache

class BitmapLruCache(maxSize: Int): LruCache<Int, Bitmap>(maxSize) { }