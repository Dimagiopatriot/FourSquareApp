package com.testapp.testapp.view;

import android.app.Activity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.testapp.testapp.R;
import com.testapp.testapp.model.entity.Photo;

import java.util.List;

/**
 * Created by troll on 07.11.2017.
 */

public class PhotosView implements CustomListView<Photo> {

    private Activity activity;
    private ImageView firstPhoto, secondPhoto, thirdPhoto;

    public PhotosView(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccessResponse(List<Photo> items) {
        firstPhoto = (ImageView) activity.findViewById(R.id.venueFirstPhoto);
        secondPhoto = (ImageView) activity.findViewById(R.id.venueSecondPhoto);
        thirdPhoto = (ImageView) activity.findViewById(R.id.venueThirdPhoto);
        ImageView[] imageViews = {firstPhoto, secondPhoto, thirdPhoto};

        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++)
                loadImageIntoContainer(imageViews[i], items.get(i).toString());
        }
    }

    @Override
    public void onStartRequest() {

    }

    @Override
    public void onFailureRequest() {
        loadImageIntoContainer(firstPhoto, "");
        loadImageIntoContainer(secondPhoto, "");
        loadImageIntoContainer(thirdPhoto, "");
    }

    @Override
    public void onEndRequest() {

    }

    private void loadImageIntoContainer(ImageView container, String url) {
        Picasso.with(activity)
                .load(url)
                .error(R.drawable.ic_broken_image)
                .into(container);
    }
}
