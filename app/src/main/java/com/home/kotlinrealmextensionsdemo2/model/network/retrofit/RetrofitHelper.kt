package com.home.kotlinrealmextensionsdemo2.model.network.retrofit

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitHelper {

    class ResponseData {
        var isSuccess: Boolean = false
        var dataBodyJsonString: String = ""
        var errorMessage: String = ""
        var httpStatusCode: Int = 0
    }

    companion object {
        const val apiBase = "https://jsonplaceholder.typicode.com/"
    }

    interface ApiService {
        @GET("photos")
        fun requestPhotos(): Call<Any>
    }

    private var apiService: ApiService
    private var responseCallback: ((responseData: ResponseData) -> Unit)? = null
    private var callback = object : Callback<Any> {
        override fun onResponse(call: Call<Any>, response: Response<Any>) {
            val responseData =
                ResponseData()
            responseData.isSuccess = response.isSuccessful
            responseData.dataBodyJsonString = Gson().toJson(response.body())
            responseData.errorMessage = response.message()
            responseData.httpStatusCode = response.code() // 取得狀態碼
            responseCallback?.invoke(responseData)
        }

        override fun onFailure(call: Call<Any>, t: Throwable) {
            val responseData =
                ResponseData()
            responseData.isSuccess = false
            responseData.errorMessage = "Server Busy!"
            responseCallback?.invoke(responseData)
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBase)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun setCallback(function: (responseData: ResponseData) -> Unit): RetrofitHelper {
        responseCallback = function
        return this
    }

    fun requestPhotosCall() {
        apiService.requestPhotos().enqueue(callback)
    }
}