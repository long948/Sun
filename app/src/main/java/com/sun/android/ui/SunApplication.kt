package com.sun.android.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunApplication : Application() {
    companion object{
//        const val TOKEN="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8zOS4xMDcuOTMuNThcL2FwaVwvbG9naW4iLCJpYXQiOjE2Mjk3MTI2NjEsImV4cCI6MTYzMTAwODY2MSwibmJmIjoxNjI5NzEyNjYxLCJqdGkiOiJmamR2TnNOcmpUYlVRU1BWIiwic3ViIjoxMjY4LCJwcnYiOiI5MWY5NjZiYTZiYzdiZGY5MTcyZWIzZDBlOWVmZTA5MmExMjRmMDAwIn0.ZA7md33hOTodpCQFakG2IMzz6D7v6eww43HeMf6BwQ0"
        const val TOKEN="V2EnJKZ960VXb22p"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}