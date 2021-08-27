package com.sun.android.logic.model

data class LnvitationListResponse(val code:Int,val msg:String,
                                  val data:LnvitationList)
data class LnvitationList(val list: List<Lnvitation>,val page :Pages)

data class Lnvitation(val node_name:String,val level :String)
data class Pages(val total:Int)


