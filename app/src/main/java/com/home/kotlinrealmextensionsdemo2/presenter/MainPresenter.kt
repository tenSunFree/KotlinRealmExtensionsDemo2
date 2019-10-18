package com.home.kotlinrealmextensionsdemo2.presenter

import com.home.kotlinrealmextensionsdemo2.model.MainModel
import com.home.kotlinrealmextensionsdemo2.model.bean.PhotosBean

class MainPresenter(private val onMainViewListener: OnMainViewListener) {

    interface OnMainViewListener {
        fun onShowLoadingProgress()
        fun onHideLoadingProgress()
    }

    private val mainModel: MainModel = MainModel()

    fun getPhotosBean(onGetMainModelListener: MainModel.OnGetMainModelListener) {
        onMainViewListener.onShowLoadingProgress()
        mainModel.getPhotosFromRealm(object : MainModel.OnGetMainModelListener {
            override fun onSuccess(photosBean: PhotosBean) {
                onMainViewListener.onHideLoadingProgress()
                onGetMainModelListener.onSuccess(photosBean)
            }

            override fun onFailure() {
                onMainViewListener.onHideLoadingProgress()
                onGetMainModelListener.onFailure()
            }
        })
    }
}