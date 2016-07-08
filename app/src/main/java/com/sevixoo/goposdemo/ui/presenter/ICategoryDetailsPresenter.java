package com.sevixoo.goposdemo.ui.presenter;

import com.sevixoo.goposdemo.ui.view.ICategoryDetailsView;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface ICategoryDetailsPresenter extends IBasicPresenter {

    void setView(ICategoryDetailsView view);
    void loadCategoryDetails( long ID );
}
