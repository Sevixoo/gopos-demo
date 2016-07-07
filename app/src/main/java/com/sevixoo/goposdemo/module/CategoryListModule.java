package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.presenter.impl.CategoryPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-07.
 */
@Module
public class CategoryListModule {

    private Context             mContext;

    public CategoryListModule(Context mContext ) {
        this.mContext = mContext;
    }

    @Provides
    public ICategoryPresenter provideLoginPresenter( AccountConfig config , CheckAuthorizationInteractor checkAuthorizationInteractor){
        return new CategoryPresenter( config , checkAuthorizationInteractor );
    }

}
