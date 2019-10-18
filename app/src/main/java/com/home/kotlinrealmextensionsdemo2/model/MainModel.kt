package com.home.kotlinrealmextensionsdemo2.model

import com.google.gson.Gson
import com.home.kotlinrealmextensionsdemo2.model.bean.Photo
import com.home.kotlinrealmextensionsdemo2.model.bean.PhotosBean
import com.home.kotlinrealmextensionsdemo2.model.network.retrofit.RetrofitHelper
import com.vicpin.krealmextensions.getRealmInstance
import com.vicpin.krealmextensions.queryAll
import com.vicpin.krealmextensions.saveAll

class MainModel {

    interface OnGetMainModelListener {
        fun onSuccess(photosBean: PhotosBean)
        fun onFailure()
    }

    /**
     * 向伺服器請求資料
     */
    private fun requestPhotosCall(onGetMainModelListener: OnGetMainModelListener) {
        val requestSuccess = 200 // 請求成功
        RetrofitHelper().setCallback { it ->
            when (it.httpStatusCode) {
                requestSuccess -> {
                    val dataBodyJsonString = "{\"photos\":" + it.dataBodyJsonString + "}"
                    val photosBean = Gson().fromJson(dataBodyJsonString, PhotosBean::class.java)
                    onGetMainModelListener.onSuccess(photosBean)
                    Array(photosBean.photos.size) {
                        Photo(
                            photosBean.photos[it].albumId, photosBean.photos[it].id,
                            photosBean.photos[it].title, photosBean.photos[it].url,
                            photosBean.photos[it].thumbnailUrl
                        )
                    }.saveAll()
                }
                else -> {
                    onGetMainModelListener.onFailure()
                }
            }
        }.requestPhotosCall()
    }

    /**
     * 向資料庫請求資料
     */
    fun getPhotosFromRealm(onGetMainModelListener: OnGetMainModelListener) {
        val userCount = Photo().getRealmInstance().where(Photo::class.java).count().toInt()
        if (userCount == 0) requestPhotosCall(onGetMainModelListener) else {
            val photosBean = PhotosBean(Photo().queryAll())
            onGetMainModelListener.onSuccess(photosBean)
        }
    }
}