package com.sevixoo.goposdemo.service.rest.pojo;

import com.sevixoo.goposdemo.domain.entity.CategoryItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoryItemsResponse implements Serializable {

    public List<CategoryItem> items;

}
