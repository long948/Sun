package com.sun.android.logic

import androidx.lifecycle.liveData
import com.sun.android.logic.model.Lnvitation
import com.sun.android.logic.model.Market
import com.sun.android.logic.model.MarketList
import com.sun.android.logic.model.Place
import com.sun.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import retrofit2.http.Header
import retrofit2.http.Query
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

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

}