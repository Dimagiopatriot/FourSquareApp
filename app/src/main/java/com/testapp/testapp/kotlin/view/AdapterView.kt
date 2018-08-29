package com.testapp.testapp.kotlin.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.testapp.testapp.kotlin.presenter.adapter.CommonRecyclerViewAdapter

/**
 * Created by dmitriysmishnyi on 29.08.18.
 */
class AdapterView<T, VH : RecyclerView.ViewHolder>(private val context: Context, private val progressBar: ProgressBar?,
                                                   val adapter: CommonRecyclerViewAdapter<T, VH>,
                                                   private val recyclerView: RecyclerView?) : CustomListView<T> {
    override fun onStartRequest() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun onFailureRequest() {
        progressBar?.visibility = View.INVISIBLE
        Toast.makeText(context, "Unfortunately, can`t load", Toast.LENGTH_SHORT).show()
    }

    override fun onEndRequest() {
        progressBar?.visibility = View.INVISIBLE
    }

    override fun onSuccessResponse(data: List<T>) {
        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        adapter.addItems(data)
    }
}