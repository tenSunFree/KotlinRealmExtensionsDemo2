package com.home.kotlinrealmextensionsdemo2.model.network.volley

import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.toolbox.ImageLoader

/**
 * 圖片緩存
 * LruBitmapCache主構造函數中, 指定一個默認值
 * LruBitmapCache帶有主構造函數, 因此超類(這裡是LruCache)必須在主構造函數中初始化
 */
class LruBitmapCache(size: Int = defaultSize) : LruCache<String, Bitmap>(size),
    ImageLoader.ImageCache {

    companion object {
        val defaultSize: Int
            get() {
                val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
                return maxMemory / 8
            }
    }

    override fun getBitmap(url: String): Bitmap? {
        return get(url)
    }

    override fun putBitmap(url: String?, bitmap: Bitmap?) {
        put(url, bitmap)
    }

    override fun sizeOf(key: String, value: Bitmap): Int {
        return value.rowBytes * value.height / 1024
    }
}