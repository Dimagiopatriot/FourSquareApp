package com.testapp.testapp.kotlin.presenter.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.testapp.testapp.kotlin.model.entity.Venue

/**
 * Created by dmitriysmishnyi on 28.08.18.
 */
class VenueAdapter: CommonRecyclerViewAdapter<Venue, VenueAdapter.Holder>() {
    override fun addItems(items: List<Venue>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearItems() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Holder(val itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}