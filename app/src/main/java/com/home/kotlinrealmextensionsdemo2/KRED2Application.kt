package com.home.kotlinrealmextensionsdemo2

import android.app.Application
import com.home.kotlinrealmextensionsdemo2.model.bean.Photo
import com.home.kotlinrealmextensionsdemo2.model.network.volley.VolleySingleton
import com.vicpin.krealmextensions.RealmConfigStore
import io.realm.Realm
import io.realm.RealmConfiguration

class KRED2Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeVolley()
        initializeRealm()
    }

    private fun initializeVolley() {
        VolleySingleton.initialize(this)
    }

    private fun initializeRealm() {
        Realm.init(this)
        val mainConfig = RealmConfiguration.Builder()
            .name("main_database") // 庫文件名
            .schemaVersion(1) // 版本號
            .deleteRealmIfMigrationNeeded() // 刪除舊版本的數據
            .build()
        RealmConfigStore.init(Photo::class.java, mainConfig) // 指定配置
    }
}
