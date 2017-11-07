package com.testapp.testapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.testapp.testapp.R;
import com.testapp.testapp.presenter.TipsPresenter;

public class VenueDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);
        setUpActivityTitle();

        String venueId = getIntent().getStringExtra("venueId");

        //TipsPresenter tipsPresenter = new TipsPresenter(this, venueId);
    }

    private void setUpActivityTitle() {
        String title = getIntent().getStringExtra("title");
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }
}
