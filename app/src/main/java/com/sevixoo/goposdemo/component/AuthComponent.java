package com.sevixoo.goposdemo.component;

import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.AuthModule;
import com.sevixoo.goposdemo.module.LoginModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.service.auth.GoPOSAuthenticatorService;
import com.sevixoo.goposdemo.ui.LoginActivity;
import com.sevixoo.goposdemo.ui.presenter.impl.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Singleton
@Component( modules={ AppModule.class, RESTApiModule.class , AuthModule.class })
public interface AuthComponent {
    void inject(GoPOSAuthenticatorService presenter);
}
