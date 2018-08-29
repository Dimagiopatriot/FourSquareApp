package com.testapp.testapp.kotlin.presenter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.testapp.testapp.R
import com.testapp.testapp.kotlin.model.entity.Tip
import com.testapp.testapp.kotlin.model.utils.SizeChanger

/**
 * Created by dmitriysmishnyi on 28.08.18.
 */
class TipAdapter(val tipList: MutableList<Tip>): CommonRecyclerViewAdapter<Tip, TipAdapter.Holder>() {

    override fun addItems(items: List<Tip>) {
        tipList.addAll(items)
        notifyDataSetChanged()
    }

    override fun clearItems() {
        tipList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.tip_item_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = tipList.size

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val currentTip = tipList[position]
        holder?.text?.text = currentTip.tipText
        holder?.likes?.text = currentTip.tipLikes.count.toString()
        holder?.userName?.text = currentTip.user.userFirstName

        val tipPhoto = currentTip.photo
        val scaleForImage = 5
        tipPhoto.width = SizeChanger.zoomOut(tipPhoto.width, scaleForImage)
        tipPhoto.height = SizeChanger.zoomOut(tipPhoto.height, scaleForImage)
        Picasso.with(holder?.itemView?.context).load(tipPhoto.toString()).into(holder?.image)

        val avatarPhoto = currentTip.user.userPhoto
        avatarPhoto.width = 40
        avatarPhoto.height = 40
        Picasso.with(holder?.itemView?.context).load(avatarPhoto.toString()).into(holder?.userAvatar)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val image = itemView.findViewById<ImageView>(R.id.tipImage)
        val userAvatar = itemView.findViewById<ImageView>(R.id.tipUserAvatar)
        val text = itemView.findViewById<TextView>(R.id.tipText)
        val userName = itemView.findViewById<TextView>(R.id.tipUserName)
        val likes = itemView.findViewById<TextView>(R.id.tipLikes)
    }
}