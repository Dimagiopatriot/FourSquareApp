package com.testapp.testapp.model.entity;

import com.google.gson.annotations.SerializedName;
import com.testapp.testapp.Constants;

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

    public String getName() {
        return name;
    }

    boolean isPrimaryCategory() {
        return isPrimaryCategory;
    }

    public Icon getCategoryIcon() {
        return categoryIcon;
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
            return urlPrefix + Constants.UI.ICON_SIZE + urlSuffix;
        }
    }
}
