package com.sevixoo.goposdemo.component;

import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.ui.presenter.impl.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Singleton
@Component(modules={AppModule.class, RESTApiModule.class})
public interface RESTApiComponent {
}
