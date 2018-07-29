package com.testapp.testapp.kotlin.model.entity.response

import com.google.gson.annotations.SerializedName
import com.testapp.testapp.kotlin.model.entity.Tip
import com.testapp.testapp.kotlin.model.entity.Wrapper

data class ResponseTips(@SerializedName("tips") val tipsWrapper: Wrapper<Tip>)