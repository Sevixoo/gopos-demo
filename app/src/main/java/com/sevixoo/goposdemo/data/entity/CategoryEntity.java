package com.sevixoo.goposdemo.data.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Seweryn on 2016-07-07.
 */
@DatabaseTable(tableName = "Categories")
public class CategoryEntity {

    @DatabaseField(generatedId = true)
    private Long _id;

    @DatabaseField()
    private String title;

    @DatabaseField
    private String imagePath;

    @DatabaseField(index = true)
    private int remoteId;

    public CategoryEntity( ) {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

}
