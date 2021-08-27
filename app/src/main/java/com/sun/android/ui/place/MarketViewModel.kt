package com.sun.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sun.android.logic.Repository
import com.sun.android.logic.model.MarketList

class MarketViewModel :ViewModel() {
    private val searchLiveData=MutableLiveData<String>()
    val marketList=ArrayList<MarketList>()
    val marketLiveData=Transformations.switchMap(searchLiveData){
        Repository.searchMarket(it)
    }
    fun searchMarket(row :String){
        searchLiveData.value=row
    }

}