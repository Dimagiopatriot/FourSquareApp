package com.testapp.testapp.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.testapp.testapp.presenter.adapter.CommonRecyclerViewAdapter;

import java.util.List;

/**
 * Created by troll on 07.11.2017.
 */

public class AdapterView<T, VH extends RecyclerView.ViewHolder> implements CustomListView<T> {

    private Context context;
    private ProgressBar loading;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<T, VH> adapter;

    public AdapterView(Context context, ProgressBar progressBar, CommonRecyclerViewAdapter<T, VH> adapter,
                       RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        loading = progressBar;
    }

    @Override
    public void onSuccessResponse(List<T> items) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.addItems(items);

    }

    @Override
    public void onStartRequest() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailureRequest() {
        loading.setVisibility(View.INVISIBLE);
        Toast.makeText(context, "Unfortunately, can`t load", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onEndRequest() {
        loading.setVisibility(View.INVISIBLE);
    }
}
