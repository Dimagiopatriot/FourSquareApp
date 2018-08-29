package com.testapp.testapp.kotlin.presenter.adapter

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.testapp.testapp.KotlinVenueDetailsActivity
import com.testapp.testapp.R
import com.testapp.testapp.kotlin.model.entity.Venue

import com.testapp.testapp.kotlin.System
import com.testapp.testapp.kotlin.model.utils.DistanceFormatter

/**
 * Created by dmitriysmishnyi on 28.08.18.
 */
class VenueAdapter(val venueList: MutableList<Venue>) : CommonRecyclerViewAdapter<Venue, VenueAdapter.Holder>() {
    override fun addItems(items: List<Venue>) {
        venueList.addAll(items)
        notifyDataSetChanged()
    }

    override fun clearItems() {
        venueList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val row = LayoutInflater.from(parent?.context).inflate(R.layout.venue_item_row, parent, false)
        return Holder(row)
    }

    override fun getItemCount(): Int = venueList.size

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val currentVenue = venueList[position]
        holder?.venueId = currentVenue.id.toInt()
        holder?.latitude = currentVenue.location.latitude
        holder?.longitude = currentVenue.location.longitude
        holder?.name?.text = currentVenue.name
        holder?.category?.text = currentVenue.primaryCategory.name
        holder?.address?.text = currentVenue.location.address
        holder?.price?.text = currentVenue.price.toString()

        if (currentVenue.location.distance != 0) {
            holder?.distance?.text = DistanceFormatter.fromMetersToKilometersFormat(currentVenue.location.distance)
        } else {
            holder?.distance?.visibility = View.GONE
        }

        holder?.rating?.text = currentVenue.rating.toString()
        holder?.rating?.setBackgroundColor(Color.parseColor("#${currentVenue.ratingColor}"))

        Picasso.with(holder?.itemView?.context).load(currentVenue.primaryCategory.icon.toString()).into(holder?.venueImage)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var venueId: Int? = null
        var latitude: Double? = null
        var longitude: Double? = null

        val venueImage = itemView.findViewById<ImageView>(R.id.venueImage)!!
        val name = itemView.findViewById<TextView>(R.id.venueName)!!
        val category = itemView.findViewById<TextView>(R.id.venueCategory)!!
        val address = itemView.findViewById<TextView>(R.id.venueAddress)!!
        val price = itemView.findViewById<TextView>(R.id.venuePrice)!!
        val distance = itemView.findViewById<TextView>(R.id.venueDistance)!!
        val rating = itemView.findViewById<TextView>(R.id.venueRating)!!

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(v?.context, KotlinVenueDetailsActivity::class.java)
            val titleName = name.text?.toString()
            intent.putExtra(System.INTENT_TITLE, titleName)
            intent.putExtra(System.INTENT_VENUE_ID, venueId)
            intent.putExtra(System.INTENT_LATITUDE, latitude)
            intent.putExtra(System.INTENT_LONGITUDE, longitude)
            v?.context?.startActivity(intent)
        }
    }
}