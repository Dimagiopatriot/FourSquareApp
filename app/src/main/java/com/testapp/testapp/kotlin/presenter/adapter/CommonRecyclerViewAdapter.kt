package com.testapp.testapp.kotlin.presenter.adapter

import android.support.v7.widget.RecyclerView

/**
 * Created by dmitriysmishnyi on 28.08.18.
 */
abstract class CommonRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    abstract fun addItems(items: List<T>)
    abstract fun clearItems()
}