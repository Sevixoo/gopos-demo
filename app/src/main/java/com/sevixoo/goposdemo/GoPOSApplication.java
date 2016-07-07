package com.sevixoo.goposdemo;

import android.app.Application;
import android.content.Context;

import com.sevixoo.goposdemo.component.AuthComponent;
import com.sevixoo.goposdemo.component.CategoryListComponent;
import com.sevixoo.goposdemo.component.DaggerAuthComponent;
import com.sevixoo.goposdemo.component.DaggerCategoryListComponent;
import com.sevixoo.goposdemo.component.DaggerLoginComponent;
import com.sevixoo.goposdemo.component.DaggerRESTApiComponent;
import com.sevixoo.goposdemo.component.LoginComponent;
import com.sevixoo.goposdemo.component.RESTApiComponent;
import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.AuthModule;
import com.sevixoo.goposdemo.module.CategoryListModule;
import com.sevixoo.goposdemo.module.LoginModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.ui.view.ICategoryView;
import com.sevixoo.goposdemo.ui.view.ILoginView;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class GoPOSApplication extends Application {

    private AppModule mAppModule;
    private RESTApiModule mRESTApiModule;
    private AuthModule mAuthModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppModule = new AppModule(this);
        mRESTApiModule = new RESTApiModule(this);
        mAuthModule = new AuthModule(this);
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
        return DaggerCategoryListComponent.builder()
                .appModule(mAppModule)
                .rESTApiModule(mRESTApiModule)
                .authModule(mAuthModule)
                .categoryListModule(new CategoryListModule(this))
                .build();
    }

}
