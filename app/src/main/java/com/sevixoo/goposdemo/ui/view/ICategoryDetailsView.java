package com.sevixoo.goposdemo.ui.view;

import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface ICategoryDetailsView {

    void showLoader();
    void hideLoader();
    void displayCategory(CategoryListItem item);
    void displayError( String message );
}
