package com.home.kotlinrealmextensionsdemo2.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.home.kotlinrealmextensionsdemo2.R
import com.home.kotlinrealmextensionsdemo2.model.MainModel
import com.home.kotlinrealmextensionsdemo2.model.bean.PhotosBean
import com.home.kotlinrealmextensionsdemo2.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.OnMainViewListener {

    private val mainPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPhotosBean()
    }

    private fun getPhotosBean() {
        mainPresenter.getPhotosBean(object : MainModel.OnGetMainModelListener {
            override fun onSuccess(photosBean: PhotosBean) {
                initializeRecyclerView(photosBean)
            }

            override fun onFailure() {
                val message = "getPhotosBean failure"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun initializeRecyclerView(photosBean: PhotosBean) {
        val adapter = MainAdapter()
        adapter.setPostList(photosBean.photos)
        val spanCount = 4
        recycler_view.layoutManager = GridLayoutManager(this, spanCount)
        recycler_view.adapter = adapter
        val cacheSize = -1
        recycler_view.setItemViewCacheSize(cacheSize) // 解決複用錯亂的問題
    }

    override fun onShowLoadingProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onHideLoadingProgress() {
        progress_bar.visibility = View.GONE
    }
}
