package com.sun.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sun.android.logic.Repository
import com.sun.android.logic.model.Lnvitation
import retrofit2.http.Header

class LnvitationViewModel : ViewModel() {
    private val liveData= MutableLiveData<String>()
    val list=ArrayList<Lnvitation>()
    val lnvitationData=Transformations.switchMap(liveData){
        Repository.lnvitation(it)
    }
    fun lnvitation(header: String){
        liveData.value=header
    }
}