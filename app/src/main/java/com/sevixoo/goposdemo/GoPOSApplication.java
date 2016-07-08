package com.sevixoo.goposdemo;

import android.app.Application;
import android.content.Context;

import com.sevixoo.goposdemo.component.AuthComponent;
import com.sevixoo.goposdemo.component.CategoryListComponent;
import com.sevixoo.goposdemo.component.DaggerAuthComponent;
import com.sevixoo.goposdemo.component.DaggerCategoryListComponent;
import com.sevixoo.goposdemo.component.DaggerLoginComponent;
import com.sevixoo.goposdemo.component.DaggerRESTApiComponent;
import com.sevixoo.goposdemo.component.DaggerSyncComponent;
import com.sevixoo.goposdemo.component.LoginComponent;
import com.sevixoo.goposdemo.component.RESTApiComponent;
import com.sevixoo.goposdemo.component.SyncComponent;
import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.AuthModule;
import com.sevixoo.goposdemo.module.CategoryListModule;
import com.sevixoo.goposdemo.module.DataModule;
import com.sevixoo.goposdemo.module.LoginModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.module.SyncModule;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class GoPOSApplication extends Application {

    private AppModule       mAppModule;
    private RESTApiModule   mRESTApiModule;
    private AuthModule      mAuthModule;
    private DataModule      mDataModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppModule = new AppModule(this);
        mRESTApiModule = new RESTApiModule(this);
        mAuthModule = new AuthModule(this);
        mDataModule = new DataModule(this);
    }

    public static GoPOSApplication get(Context context){
        return (GoPOSApplication)context.getApplicationContext();
    }

    public AuthComponent getAuthComponent(){
        return DaggerAuthComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .authModule(mAuthModule)
                .build();
    }

    public RESTApiComponent getRESTApiComponent(){
        return DaggerRESTApiComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .build();
    }

    public LoginComponent getLoginComponent( boolean canAddAccount){
        return DaggerLoginComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .authModule(mAuthModule)
                .loginModule(new LoginModule(this, canAddAccount))
                .build();
    }

    public CategoryListComponent getCategoryListComponent(){
        String authority = getString(R.string.config_category_sync_authority);
        return DaggerCategoryListComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .authModule(mAuthModule)
                .categoryListModule(new CategoryListModule(this))
                .syncModule(new SyncModule(this,authority))
                .dataModule(mDataModule)
                .build();
    }
    public SyncComponent getSyncCategoriesComponent(){
        String authority = getString(R.string.config_category_sync_authority);
        return DaggerSyncComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .authModule(mAuthModule)
                .syncModule(new SyncModule(this,authority))
                .dataModule(mDataModule)
                .build();
    }


}
