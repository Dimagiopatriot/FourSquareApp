package com.testapp.testapp.view;

import java.util.List;

/**
 * Created by troll on 06.11.2017.
 */

public interface CustomListView<T> extends CustomView {

    void onSuccessResponse(List<T> items);
}
