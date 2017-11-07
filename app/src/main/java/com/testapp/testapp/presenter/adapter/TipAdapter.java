package com.testapp.testapp.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testapp.testapp.R;
import com.testapp.testapp.model.entity.Tip;

import java.util.List;

/**
 * Created by troll on 06.11.2017.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.Holder> {

    private List<Tip> tips;

    public void addVenues(List<Tip> tips){
        this.tips.addAll(tips);
        notifyDataSetChanged();
    }

    public void clearVenues(){
        tips.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_item_row, parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Tip tip = tips.get(position);

        holder.text.setText(tip.getTipText());
        holder.likes.setText(tip.getTipLikes().getCount());
        holder.userName.setText(tip.getUser().getUserFirstName());

        Context context = holder.itemView.getContext();
        Picasso.with(context).load(tip.getPhoto().toString()).into(holder.image);
        Picasso.with(context).load(tip.getUser().getUserPhoto().toString()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image, userAvatar;
        TextView text, userName, likes;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.tipImage);
            userAvatar = (ImageView) itemView.findViewById(R.id.tipUserAvatar);
            text = (TextView) itemView.findViewById(R.id.tipText);
            userName = (TextView) itemView.findViewById(R.id.tipUserName);
            likes = (TextView) itemView.findViewById(R.id.tipLikes);
        }

        @Override
        public void onClick(View v) {
            //Nothing to do
        }
    }
}
