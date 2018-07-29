package com.testapp.testapp.kotlin.model.entity

import com.google.gson.annotations.SerializedName

data class Venue(@SerializedName("id") val id: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("categories") val categories: List<Category>,
                 var primaryCategory: Category,
                 @SerializedName("price") val price: Price,
                 @SerializedName("location") val location: Location,
                 @SerializedName("rating") val rating: Double,
                 @SerializedName("ratingColor") val ratingColor: String,
                 @SerializedName("description") val description: String,
                 val photoWrapper: Wrapper<Photo>) {

    fun setPrimaryCategory() {
        for (category in categories) {
            if (category.primary) {
                primaryCategory = category
                break
            }
        }
    }
}