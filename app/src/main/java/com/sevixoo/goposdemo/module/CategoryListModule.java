package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignUpInteractor;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.presenter.impl.CategoryPresenter;
import com.sevixoo.goposdemo.ui.presenter.impl.LoginPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;
import com.sevixoo.goposdemo.ui.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-07.
 */
@Module
public class CategoryListModule {

    private Context             mContext;
    private ICategoryView       mCategoryView;

    public CategoryListModule(Context mContext, ICategoryView categoryView ) {
        this.mContext = mContext;
        this.mCategoryView = categoryView;
    }

    @Provides
    public ICategoryView provideCategoryView(){
        return mCategoryView;
    }

    @Provides
    public ICategoryPresenter provideLoginPresenter(ICategoryView categoryView, AccountConfig config , CheckAuthorizationInteractor checkAuthorizationInteractor){
        return new CategoryPresenter( categoryView ,config , checkAuthorizationInteractor );
    }


}
