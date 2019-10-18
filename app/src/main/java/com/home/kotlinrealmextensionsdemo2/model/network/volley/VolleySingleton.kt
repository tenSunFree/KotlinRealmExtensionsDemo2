package com.home.kotlinrealmextensionsdemo2.model.network.volley

import android.annotation.SuppressLint
import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

/**
 * object用於單例模式
 * object聲明對象名後, 通過對象名來訪問, 且不能=右邊賦值
 */
@SuppressLint("StaticFieldLeak")
object VolleySingleton {

    private lateinit var context: Context

    /**
     * 這裡使用延遲屬性(lazy properties), 首次訪問時計算結果, 以後再次訪問時候, 將拷貝第一次記錄的結果
     * lazy屬性的計算結果的過程是同步鎖的(synchronized)
     */
    private val requestQueque: RequestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    val imageLoader: ImageLoader by lazy {
        ImageLoader(requestQueque, LruBitmapCache())
    }

    fun initialize(context: Context) {
        VolleySingleton.context = context.applicationContext
    }
}
