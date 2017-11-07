package com.testapp.testapp.presenter.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.testapp.testapp.R;
import com.testapp.testapp.view.VenueDetailsActivity;
import com.testapp.testapp.model.entity.Photo;
import com.testapp.testapp.model.entity.Venue;
import com.testapp.testapp.model.utils.DistanceFormatter;

import java.util.List;

/**
 * Created by troll on 05.11.2017.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.Holder> {

    private List<Venue> venues;

    public void addVenues(List<Venue> venues){
        this.venues.addAll(venues);
        notifyDataSetChanged();
    }

    public void clearVenues(){
        venues.clear();
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item_row, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Venue currentVenue = venues.get(position);

        holder.setVenueId(currentVenue.getId());
        holder.name.setText(currentVenue.getName());
        holder.category.setText(currentVenue.getPrimaryCategory().getName());
        holder.address.setText(currentVenue.getLocation().getAddress());
        holder.price.setText(currentVenue.getPrice().toString());
        holder.distance.setText(DistanceFormatter.fromMetersToKilometersFormat(currentVenue.getLocation().getDistance()));

        holder.rating.setText(String.valueOf(currentVenue.getRating()));
        holder.rating.setBackgroundColor(Color.parseColor("#" + currentVenue.getRatingColor()));
        checkVenuePhotos(holder, currentVenue);
    }

    private void checkVenuePhotos(Holder holder, Venue venue){
        if (venue.getPhotoWrapper() == null){
            setVenueImage(holder, venue.getPrimaryCategory().getCategoryIcon().toString());
        } else {
            Photo venuePhoto = venue.getPhotoWrapper().getItems().get(0);
            if (venuePhoto != null){
                venuePhoto.zoomOutWidth();
                venuePhoto.zoomOutHeiht();
                setVenueImage(holder, venuePhoto.toString());
            }
        }
    }

    private void setVenueImage(Holder holder, String url){
        Picasso.with(holder.itemView.getContext()).load(url).into(holder.venueImage);
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView venueImage;
        TextView name, category, address, price, distance, rating;

        String venueId;

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            venueImage = (ImageView) itemView.findViewById(R.id.venueImage);
            name = (TextView) itemView.findViewById(R.id.venueName);
            category = (TextView) itemView.findViewById(R.id.venueCategory);
            address = (TextView) itemView.findViewById(R.id.venueAddress);
            price = (TextView) itemView.findViewById(R.id.venuePrice);
            distance = (TextView) itemView.findViewById(R.id.venueDistance);
            rating = (TextView) itemView.findViewById(R.id.venueRating);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), VenueDetailsActivity.class);
            String nameForTitle = name.getText().toString();
            intent.putExtra("title", nameForTitle);
            intent.putExtra("venueId", venueId);
            v.getContext().startActivity(intent);
        }
    }
}
