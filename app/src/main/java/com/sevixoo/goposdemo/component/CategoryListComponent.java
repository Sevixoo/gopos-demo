package com.sevixoo.goposdemo.component;

import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.AuthModule;
import com.sevixoo.goposdemo.module.CategoryListModule;
import com.sevixoo.goposdemo.module.LoginModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.ui.CategoryListActivity;
import com.sevixoo.goposdemo.ui.LoginActivity;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Singleton
@Component( modules={ AppModule.class, RESTApiModule.class , AuthModule.class , CategoryListModule.class  } )
public interface CategoryListComponent {
    void inject(CategoryListActivity activity);
    ICategoryPresenter getCategoryPresenter();
}
