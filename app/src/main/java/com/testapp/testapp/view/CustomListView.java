package com.testapp.testapp.view;

import java.util.List;

/**
 * Created by troll on 06.11.2017.
 */

public interface CustomListView extends CustomView {

    void onSuccessResponse(List<?> items);
}
