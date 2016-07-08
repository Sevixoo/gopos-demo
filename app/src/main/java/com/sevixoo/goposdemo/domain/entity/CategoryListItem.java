package com.sevixoo.goposdemo.domain.entity;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoryListItem {

    private long _id;
    private String name;
    private String color;
    private String imagePath;

    public CategoryListItem(String name, String color, String imagePath) {
        this.name = name;
        this.color = color;
        this.imagePath = imagePath;
    }

    public CategoryListItem () {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagePath() {
        return imagePath;
    }

    public long getID() {
        return _id;
    }

    public void setID(long _id) {
        this._id = _id;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
