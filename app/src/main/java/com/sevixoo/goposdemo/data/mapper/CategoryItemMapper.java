package com.sevixoo.goposdemo.data.mapper;

import com.sevixoo.goposdemo.data.entity.CategoryEntity;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoryItemMapper {

    public CategoryItem transform(CategoryEntity entity){
        CategoryItem item = new CategoryItem();
        item.setID(entity.get_id());
        item.setImagePath(entity.getImagePath());
        item.setName(entity.getTitle());
        item.setRemoteId(entity.getRemoteId());
        return item;
    }

    public CategoryEntity transform(CategoryItem item){
        CategoryEntity entity = new CategoryEntity();
        entity.setRemoteId(item.getRemoteId());
        entity.setImagePath(item.getImagePath());
        entity.set_id(item.getID());
        entity.setTitle(item.getName());
        return entity;
    }
}
