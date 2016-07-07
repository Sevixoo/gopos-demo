package com.sevixoo.goposdemo.ui.presenter;

import com.sevixoo.goposdemo.ui.view.ICategoryView;

/**
 * Created by Seweryn on 2016-07-07.
 */
public interface ICategoryPresenter extends IBasicPresenter {

    void checkLogin();
    void setView(ICategoryView view);
    void onClickLogout();
}
