package com.testapp.testapp.kotlin.view

import android.app.Activity
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.testapp.testapp.R
import com.testapp.testapp.kotlin.model.entity.Photo

/**
 * Created by dmitriysmishnyi on 29.08.18.
 */
class PhotosView(private val activity: Activity) : CustomListView<Photo> {

    private val firstPhoto = activity.findViewById<ImageView>(R.id.venueFirstPhoto)!!
    private val secondPhoto = activity.findViewById<ImageView>(R.id.venueSecondPhoto)!!
    private val thirdPhoto = activity.findViewById<ImageView>(R.id.venueThirdPhoto)!!

    override fun onStartRequest() {}

    override fun onFailureRequest() {
        loadImageIntoContainer(firstPhoto, "")
        loadImageIntoContainer(secondPhoto, "")
        loadImageIntoContainer(thirdPhoto, "")
    }

    override fun onEndRequest() {}

    override fun onSuccessResponse(data: List<Photo>) {
        val imageViews = listOf(firstPhoto, secondPhoto, thirdPhoto)
        if (!data.isEmpty()) {
            for ((index, dataItem) in data.withIndex())
                loadImageIntoContainer(imageViews[index], dataItem.toString())
        }
    }

    private fun loadImageIntoContainer(container: ImageView, url: String) {
        Picasso.with(activity).load(url).error(R.drawable.ic_broken_image).into(container)
    }
}