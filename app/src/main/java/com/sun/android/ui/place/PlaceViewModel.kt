package com.sun.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sun.android.logic.Repository
import com.sun.android.logic.model.Place

class PlaceViewModel :ViewModel() {
    private val searchLiveData=MutableLiveData<String>()
    val placeList=ArrayList<Place>()
    val placeLiveData=Transformations.switchMap(searchLiveData){
        Repository.searchPlaces(it)
    }
    fun searchPlaces(query:String){
        searchLiveData.value=query
    }
}