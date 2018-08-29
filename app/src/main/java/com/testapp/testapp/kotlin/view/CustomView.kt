package com.testapp.testapp.kotlin.view

/**
 * Created by dmitriysmishnyi on 29.08.18.
 */
interface CustomView<T> {
    fun onStartRequest()
    fun onFailureRequest()
    fun onEndRequest()
    fun onSuccessResponse(data: T)
}