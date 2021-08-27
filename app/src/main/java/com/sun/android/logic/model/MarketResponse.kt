package com.sun.android.logic.model

data class MarketResponse(val code:Int,val msg:String,val data: Market)
data class Market(val list: List<MarketList>,val page:Page)
data class Page(val count:Int)
data class MarketList(val id:String,val name: String)