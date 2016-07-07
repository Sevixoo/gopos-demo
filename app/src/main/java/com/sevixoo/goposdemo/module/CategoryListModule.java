package com.sevixoo.goposdemo.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.domain.interactor.DeleteAccountInteractor;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.adapter.CategoriesRecyclerAdapter;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.presenter.impl.CategoryPresenter;

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
    public ICategoryPresenter provideLoginPresenter(AccountConfig config , DeleteAccountInteractor deleteAccountInteractor, CheckAuthorizationInteractor checkAuthorizationInteractor){
        return new CategoryPresenter( config ,deleteAccountInteractor, checkAuthorizationInteractor );
    }

    @Provides
    public CategoriesRecyclerAdapter provideCategoryAdapter(){
        return new CategoriesRecyclerAdapter();
    }

}
