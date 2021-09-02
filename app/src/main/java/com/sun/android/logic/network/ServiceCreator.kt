package com.sun.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceCreator {
    private const val BASE_URL="https://api.caiyunapp.com/"
    private const val TESTBASE_URL="http://39.107.93.58/"
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass :Class<T>):T= retrofit.create(serviceClass)
    inline fun <reified T> create():T= create(T::class.java)
}


