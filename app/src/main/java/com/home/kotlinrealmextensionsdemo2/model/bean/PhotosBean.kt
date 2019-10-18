package com.home.kotlinrealmextensionsdemo2.model.bean

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

data class PhotosBean(
    @SerializedName("photos")
    val photos: List<Photo>
)

open class Photo(
    @SerializedName("albumId")
    var albumId: Int?=null,
    @SerializedName("id")
    var id: Int?=null,
    @SerializedName("title")
    var title: String?=null,
    @SerializedName("url")
    var url: String?=null,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String?=null
) : RealmObject()