package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.model.utils.Constants;

/**
 * Created by troll on 04.11.2017.
 */

public class Category {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("primary")
    private boolean isPrimaryCategory;
    @SerializedName("icon")
    private Icon categoryIcon;

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

    public boolean isPrimaryCategory() {
        return isPrimaryCategory;
    }

    public void setPrimaryCategory(boolean primaryCategory) {
        isPrimaryCategory = primaryCategory;
    }

    public Icon getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(Icon categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (isPrimaryCategory != category.isPrimaryCategory) return false;
        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return categoryIcon != null ? categoryIcon.equals(category.categoryIcon) : category.categoryIcon == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isPrimaryCategory ? 1 : 0);
        result = 31 * result + (categoryIcon != null ? categoryIcon.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isPrimaryCategory=" + isPrimaryCategory +
                ", categoryIcon=" + categoryIcon +
                '}';
    }

    public class Icon{

        @SerializedName("prefix")
        private String urlPrefix;
        @SerializedName("suffix")
        private String urlSuffix;

        @Override
        public String toString() {
            return urlPrefix + Constants.ICON_SIZE + urlSuffix;
        }
    }
}
