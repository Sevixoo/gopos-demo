package com.sevixoo.goposdemo.ui.presenter.impl;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.domain.interactor.LoadCategoriesDetailsInteractor;
import com.sevixoo.goposdemo.ui.presenter.ICategoryDetailsPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryDetailsView;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class CategoryDetailsPresenter implements ICategoryDetailsPresenter {

    private ICategoryDetailsView            mCategoryDetailsView;
    private LoadCategoriesDetailsInteractor mLoadCategoriesDetailsInteractor;

    public CategoryDetailsPresenter(LoadCategoriesDetailsInteractor mLoadCategoriesDetailsInteractor) {
        this.mLoadCategoriesDetailsInteractor = mLoadCategoriesDetailsInteractor;
    }

    @Override
    public void setView(ICategoryDetailsView view) {
        mCategoryDetailsView = view;
    }

    @Override
    public void loadCategoryDetails(long ID) {
        mCategoryDetailsView.showLoader();
        mLoadCategoriesDetailsInteractor.execute( ID , new DefaultSubscriber<CategoryListItem>(){
            @Override
            public void onNext(CategoryListItem categoryItem) {
                mCategoryDetailsView.hideLoader();
                mCategoryDetailsView.displayCategory( categoryItem );
            }

            @Override
            public void onError(Throwable e) {
                mCategoryDetailsView.hideLoader();
                mCategoryDetailsView.displayError(e.getMessage());
            }
        });
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
