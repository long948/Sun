package com.sun.android.logic.network

import com.sun.android.logic.model.Lnvitation
import com.sun.android.ui.SunApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.http.Header
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork{
 val placeService=ServiceCreator.create<PlaceService>()
suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await()
suspend fun Lnvitation(header: String)= placeService.getList(header).await()
suspend fun searchMarket(row :String)= placeService.getMarket("Bearer "+SunApplication.TOKEN,row).await()
    private suspend fun <T> Call<T>.await():T{
    return suspendCoroutine {
        enqueue(object :Callback<T>{
            override fun onFailure(call: Call<T>, t: Throwable) {
            it.resumeWithException(t)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
            val body=response.body()
                if (body!=null) it.resume(body)
                else it.resumeWithException(RuntimeException("body is null"))
            }
        })
    }
}
}