package com.testapp.testapp.presenter.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by troll on 07.11.2017.
 */

public abstract class CommonRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public abstract void addItems(List<T> items);
    public abstract void clearItems();
}
