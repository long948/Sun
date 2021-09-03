package com.sun.android.ui.place

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.R
import com.sun.android.logic.model.Lnvitation
import com.sun.android.logic.model.MarketList
import com.sun.android.logic.model.Place
import com.sun.android.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.invitation_item.view.*

class PlaceAdapter(private val fragment: PlaceFragment,
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
        holder.placeLayout.setOnClickListener {
//            val place=placeList[position]
            val activity=fragment.activity
            if (activity is WeatherActivity){
                activity.drawerLayout.closeDrawers()
                activity.viewModel.locationLng=place.location.lng
                activity.viewModel.locationLat=place.location.lat
                activity.viewModel.placeName=place.name
                activity.refreshWeather()
            }else{
                val intent=Intent(fragment.activity,WeatherActivity::class.java).apply {
                    putExtra("location_lng", place.location.lng)
                    putExtra("location_lat", place.location.lat)
                    putExtra("place_name", place.name)
            }
                fragment.startActivity(intent)
                fragment.activity?.finish()
            }
                fragment.viewModel.savePlace(place)
        }
    }


    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val placeName=view.placeName
        val placeAddress=view.placeAddress
        val placeLayout=view.placeLayout
    }
}