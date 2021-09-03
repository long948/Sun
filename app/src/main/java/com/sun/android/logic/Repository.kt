package com.sun.android.logic

import androidx.lifecycle.liveData
import com.sun.android.logic.dao.PlaceDao
import com.sun.android.logic.model.*
import com.sun.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.http.Header
import retrofit2.http.Query
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {
    fun savePlace(place: Place)=PlaceDao.savePlace(place)

    fun getSavedPlace()=PlaceDao.getSavedPlace()

    fun isPlaceSaved()=PlaceDao.isPlaceSaved()


    fun searchMarket(row:String)= liveData(Dispatchers.IO){
        val result=try {
            val marketResponse=SunnyWeatherNetwork.searchMarket(row)
            if (marketResponse.code==0){
                val market=marketResponse.data.list
                Result.success(market)
            }else{
                Result.failure(RuntimeException("response code is ${marketResponse.code}"))
            }
        }catch (e :Exception){
            Result.failure<List<MarketList>>(e)
        }
        emit(result)
    }

    fun lnvitation(header: String)= liveData(Dispatchers.IO){
        val result=try {
            val lnvitationList=SunnyWeatherNetwork.Lnvitation(header)
                if (lnvitationList.code==0){
                    val list=lnvitationList.data.list
                    Result.success(list)
                }else{
                  Result.failure(RuntimeException("错误"+lnvitationList.code))
                }
        }catch (e :Exception){
            Result.failure<List<Lnvitation>>(e)
        }
        emit(result)
    }

    fun searchPlaces(query: String)= liveData(Dispatchers.IO){
        val result=try {
            val placeResponse=SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status=="ok"){
                val places=placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e :Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun refreshWeather(lng:String,lat:String)= liveData(Dispatchers.IO){
        val result=try {
            coroutineScope {
                val deferredRealtime=async {
                    SunnyWeatherNetwork
                        .getRealtimeWeather(lng, lat)
                }
                val deferredDaily=async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse=deferredRealtime.await()
                val dailyResponse=deferredDaily.await()
                if (realtimeResponse.status=="ok" && dailyResponse.status=="ok"){
                    val weather=Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                   "realtime response status is ${realtimeResponse.status}" +
                    "daily response status is ${dailyResponse.status}"
 )
                    )
                }
            }

        }catch (e :Exception){
        Result.failure<Weather>(e)
        }
        emit(result)
    }
    private fun <T>fire(context:CoroutineContext,block:suspend()->Result<T>)=
        liveData<Result<T>>(context){
            val result=try {
                block()
            }catch (e :Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }
}