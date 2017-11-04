package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by troll on 03.11.2017.
 */

public class Venue {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("categories")
    private List<Category> categories;
    private Category primaryCategory;
    @SerializedName("price")
    private Price price;
    @SerializedName("location")
    private Location location;
    @SerializedName("rating")
    private double rating;
    @SerializedName("ratingColor")
    private String ratingColor;
    @SerializedName("description")
    private String description;
    private Wrapper<Photo> photoWrapper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory() {
        for (Category category : categories){
            if (category.isPrimaryCategory()){
                primaryCategory = category;
                break;
            }
        }
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wrapper<Photo> getPhotoWrapper() {
        return photoWrapper;
    }

    public void setPhotoWrapper(Wrapper<Photo> photoWrapper) {
        this.photoWrapper = photoWrapper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        if (Double.compare(venue.rating, rating) != 0) return false;
        if (id != null ? !id.equals(venue.id) : venue.id != null) return false;
        if (name != null ? !name.equals(venue.name) : venue.name != null) return false;
        if (categories != null ? !categories.equals(venue.categories) : venue.categories != null)
            return false;
        if (primaryCategory != null ? !primaryCategory.equals(venue.primaryCategory) : venue.primaryCategory != null)
            return false;
        if (price != null ? !price.equals(venue.price) : venue.price != null) return false;
        if (location != null ? !location.equals(venue.location) : venue.location != null)
            return false;
        if (ratingColor != null ? !ratingColor.equals(venue.ratingColor) : venue.ratingColor != null)
            return false;
        if (description != null ? !description.equals(venue.description) : venue.description != null)
            return false;
        return photoWrapper != null ? photoWrapper.equals(venue.photoWrapper) : venue.photoWrapper == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (primaryCategory != null ? primaryCategory.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ratingColor != null ? ratingColor.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoWrapper != null ? photoWrapper.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", primaryCategory=" + primaryCategory +
                ", price=" + price +
                ", location=" + location +
                ", rating=" + rating +
                ", ratingColor='" + ratingColor + '\'' +
                ", description='" + description + '\'' +
                ", photoWrapper=" + photoWrapper +
                '}';
    }
}
