package com.sevixoo.goposdemo.ui.presenter.impl;

import android.util.Log;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignUpInteractor;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView              mLoginView;
    private UserSignUpInteractor    mUserSignUpInteractor;
    private CreateAccountInteractor mCreateAccountInteractor;
    private boolean                 canAddAccount;

    public LoginPresenter(ILoginView loginView, UserSignUpInteractor userSignUpInteractor, CreateAccountInteractor mCreateAccountInteractor) {
        this.mLoginView = loginView;
        this.mUserSignUpInteractor = userSignUpInteractor;
        this.mCreateAccountInteractor = mCreateAccountInteractor;
        Log.e("LoginPresenter","LoginPresenter");
    }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onUserSignIn(String login, String password, String tokenType) {
        mLoginView.showProgressLoader();
        mUserSignUpInteractor.execute( new LoginSubscriber() );
    }

    private class LoginSubscriber extends DefaultSubscriber<SignUpCredentials>{
        @Override
        public void onNext(SignUpCredentials res) {
            mCreateAccountInteractor.execute( canAddAccount , res , new CreateAccountSubscriber() );
        }
        @Override
        public void onError(Throwable e) {
            mLoginView.hideProgressLoader();
            mLoginView.displayLoginError(e.getMessage());
        }
    }

    private class CreateAccountSubscriber extends DefaultSubscriber<String>{
        @Override
        public void onCompleted() {
            mLoginView.hideProgressLoader();
        }
        @Override
        public void onError(Throwable e) {
            mLoginView.hideProgressLoader();
            mLoginView.displayLoginError(e.getMessage());
        }
    }

}
