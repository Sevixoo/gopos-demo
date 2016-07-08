package com.sevixoo.goposdemo.domain.repository;

import com.sevixoo.goposdemo.domain.entity.CategoryItem;

import java.sql.SQLException;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface ICategoriesRepository extends IBasicRepository<CategoryItem> {

    CategoryItem getByRemoteID( int remoteID )throws SQLException;

}
