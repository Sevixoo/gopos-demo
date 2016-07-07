package com.sevixoo.goposdemo.ui.view;

import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;

import java.util.List;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface ICategoryView {


    void showProgressLoader();

    void displayError(String error);

    void hideProgressLoader();

    void showScreenLoader();

    void hideScreenLoader();

    void showAuthorizationPage();

    void onItemsLoaded( List<CategoryListItem> listItems );

    void onLoginSuccess();
}
