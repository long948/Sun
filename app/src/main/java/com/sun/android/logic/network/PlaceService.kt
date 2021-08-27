package com.sun.android.logic.network


import com.sun.android.logic.model.LnvitationListResponse
import com.sun.android.logic.model.MarketResponse
import com.sun.android.logic.model.PlaceResponse
import com.sun.android.ui.SunApplication
import retrofit2.Call
import retrofit2.http.*

interface PlaceService{



    @GET("v2/place?token=${SunApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String) : Call<PlaceResponse>

    @POST("api/node/list")
    fun getList( @Header("Authorization") token: String?) : Call<LnvitationListResponse>

    @GET("api/market/ticks")
    fun getMarket(
        @Header("Authorization") token: String?,
        @Query("row") row: String ):Call<MarketResponse>

}