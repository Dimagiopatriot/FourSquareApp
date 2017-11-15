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
import com.testapp.testapp.model.entity.Photo;
import com.testapp.testapp.model.entity.Tip;
import com.testapp.testapp.model.utils.Constants;
import com.testapp.testapp.model.utils.SizeChanger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by troll on 06.11.2017.
 */

public class TipAdapter extends CommonRecyclerViewAdapter<Tip, TipAdapter.Holder> {

    private List<Tip> tips = new ArrayList<>();

    @Override
    public void addItems(List<Tip> items) {
        tips.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void clearItems() {
        tips.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_item_row, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Tip tip = tips.get(position);

        holder.text.setText(tip.getTipText());
        holder.likes.setText(String.valueOf(tip.getTipLikes().getCount()));
        holder.userName.setText(tip.getUser().getUserFirstName());

        Context context = holder.itemView.getContext();
        if (tip.getPhoto() != null) {
            Photo tipPhoto = tip.getPhoto();

            int scaleForImage = 5;

            tipPhoto.setWidth(SizeChanger.zoomOut(tipPhoto.getWidth(), scaleForImage));
            tipPhoto.setHeight(SizeChanger.zoomOut(tipPhoto.getHeight(), scaleForImage));
            Picasso.with(context).load(tipPhoto.toString()).into(holder.image);
        }
        Photo userAvatar = tip.getUser().getUserPhoto();

        int widthInPx = 40;
        int heightInPx = 40;

        userAvatar.setWidth(widthInPx);
        userAvatar.setHeight(heightInPx);
        Picasso.with(context).load(userAvatar.toString()).into(holder.userAvatar);
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image, userAvatar;
        TextView text, userName, likes;

        Holder(View itemView) {
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
