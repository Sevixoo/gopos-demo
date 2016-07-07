package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignUpInteractor;
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
    private ILoginView      mLoginView;
    private boolean         canAddAccount;

    public LoginModule(Context mContext, ILoginView mLoginView, boolean canAddAccount) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
        this.canAddAccount = canAddAccount;
    }

    @Provides
    public ILoginView provideLoginView(){
        return mLoginView;
    }

    @Provides
    public ILoginPresenter provideLoginPresenter(ILoginView loginView, UserSignUpInteractor userSignUpInteractor, CreateAccountInteractor mCreateAccountInteractor ){
        return new LoginPresenter( loginView ,userSignUpInteractor , mCreateAccountInteractor );
    }

}
