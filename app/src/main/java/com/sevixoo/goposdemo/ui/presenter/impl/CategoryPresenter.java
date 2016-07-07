package com.sevixoo.goposdemo.ui.presenter.impl;

import android.util.Log;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoryPresenter implements ICategoryPresenter {

    ICategoryView                       mCategoryView;
    CheckAuthorizationInteractor        mCheckAuthorizationInteractor;
    AccountConfig                       mAccountConfig;

    public CategoryPresenter(ICategoryView categoryView,AccountConfig accountConfig, CheckAuthorizationInteractor checkAuthorizationInteractor) {
        mCheckAuthorizationInteractor = checkAuthorizationInteractor;
        mCategoryView = categoryView;
        mAccountConfig = accountConfig;
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

    @Override
    public void checkLogin() {
        mCategoryView.showScreenLoader();
        mCheckAuthorizationInteractor.execute(
                mAccountConfig.getCategoryTokenType(),
                new CheckLoginSubscriber()
        );
    }

    private class CheckLoginSubscriber extends DefaultSubscriber<String>{
        @Override
        public void onNext(String authToken) {
            mCategoryView.hideScreenLoader();
        }
        @Override
        public void onError(Throwable e) {
            mCategoryView.showAuthorizationPage();
        }
    }

}
