package com.sevixoo.goposdemo.domain.entity;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoryItem {

    private long _id;
    private int remoteId;
    private String name;
    private String imagePath;

    public CategoryItem( ) {
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public long getID() {
        return _id;
    }

    public void setID(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

