package com.testapp.testapp.view;

/**
 * Created by troll on 07.11.2017.
 */

public interface CustomItemView<T> extends CustomView {

    void onSuccessResponse(T item);
}
