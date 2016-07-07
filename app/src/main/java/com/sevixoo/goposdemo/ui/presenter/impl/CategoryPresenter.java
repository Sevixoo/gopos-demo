package com.sevixoo.goposdemo.ui.presenter.impl;

import android.util.Log;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.domain.interactor.DeleteAccountInteractor;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoryPresenter implements ICategoryPresenter {

    ICategoryView                       mCategoryView;
    CheckAuthorizationInteractor        mCheckAuthorizationInteractor;
    DeleteAccountInteractor             mDeleteAccountInteractor;
    AccountConfig                       mAccountConfig;

    public CategoryPresenter( AccountConfig accountConfig,DeleteAccountInteractor deleteAccountInteractor, CheckAuthorizationInteractor checkAuthorizationInteractor) {
        mCheckAuthorizationInteractor = checkAuthorizationInteractor;
        mDeleteAccountInteractor = deleteAccountInteractor;
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

    @Override
    public void setView(ICategoryView categoryView) {
        mCategoryView = categoryView;
    }

    @Override
    public void onClickLogout() {
        mCategoryView.showScreenLoader();
        mDeleteAccountInteractor.execute(new LogoutSubscriber());
    }

    @Override
    public void loadCategories(int offset) {
        List<CategoryListItem> items = new ArrayList<>();
        items.add( new CategoryListItem("AAA","","") );
        items.add( new CategoryListItem("BBB","","") );
        items.add( new CategoryListItem("CCC","","") );
        items.add( new CategoryListItem("DDD","","") );
        items.add( new CategoryListItem("EEE","","") );
        mCategoryView.onItemsLoaded(items);
    }

    private class LogoutSubscriber extends DefaultSubscriber<String>{
        @Override
        public void onCompleted() {
            mCategoryView.showAuthorizationPage();
        }
        @Override
        public void onError(Throwable e) {
            mCategoryView.displayError(e.getMessage());
        }
    }

    private class CheckLoginSubscriber extends DefaultSubscriber<String>{
        @Override
        public void onNext(String authToken) {
            mCategoryView.hideScreenLoader();
            mCategoryView.onLoginSuccess();
        }
        @Override
        public void onError(Throwable e) {
            mCategoryView.showAuthorizationPage();
        }
    }

}
