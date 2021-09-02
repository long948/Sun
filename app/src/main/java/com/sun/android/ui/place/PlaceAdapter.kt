package com.sun.android.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.R
import com.sun.android.logic.model.Lnvitation
import com.sun.android.logic.model.MarketList
import com.sun.android.logic.model.Place
import kotlinx.android.synthetic.main.invitation_item.view.*

class PlaceAdapter(private val fragment: Fragment,
                   private val placeList:List<Place>) :
                    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.invitation_item,parent,false
        ))
    }

    override fun getItemCount(): Int =placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place=placeList[position]
        holder.placeName.text=place.name
        holder.placeAddress.text=place.address
    }


    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val placeName=view.placeName
        val placeAddress=view.placeAddress

    }
}