package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignInInteractor;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.presenter.impl.LoginPresenter;
import com.sevixoo.goposdemo.ui.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Module
public class LoginModule {

    private Context         mContext;
    private boolean         canAddAccount;

    public LoginModule(Context mContext, boolean canAddAccount) {
        this.mContext = mContext;
        this.canAddAccount = canAddAccount;
    }

    @Provides
    public ILoginPresenter provideLoginPresenter(  UserSignInInteractor userSignUpInteractor, CreateAccountInteractor mCreateAccountInteractor ){
        return new LoginPresenter(canAddAccount ,userSignUpInteractor , mCreateAccountInteractor );
    }

}
