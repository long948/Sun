package com.sun.android.logic.network


import com.sun.android.logic.model.*
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

    @GET("v2.5/${SunApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>


    @GET("v2.5/${SunApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>
}