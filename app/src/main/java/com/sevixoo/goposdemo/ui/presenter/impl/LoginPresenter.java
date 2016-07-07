package com.sevixoo.goposdemo.ui.presenter.impl;

import android.util.Log;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignInInteractor;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.view.ILoginView;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView                  mLoginView;
    private UserSignInInteractor        mUserSignUpInteractor;
    private CreateAccountInteractor     mCreateAccountInteractor;
    private boolean                     canAddAccount;

    public LoginPresenter(boolean canAddAccount , UserSignInInteractor userSignUpInteractor, CreateAccountInteractor mCreateAccountInteractor) {
        this.mUserSignUpInteractor = userSignUpInteractor;
        this.mCreateAccountInteractor = mCreateAccountInteractor;
        this.canAddAccount = canAddAccount;
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
        mUserSignUpInteractor.execute( login , password, tokenType,  new LoginSubscriber() );
    }

    @Override
    public void setView(ILoginView loginView) {
        this.mLoginView = loginView;
    }

    private class LoginSubscriber extends DefaultSubscriber<SignUpCredentials>{
        @Override
        public void onNext(SignUpCredentials res) {
            Log.e( "LoginSubscriber" , "onNext" );
            mCreateAccountInteractor.execute( canAddAccount , res , new CreateAccountSubscriber() );
        }
        @Override
        public void onError(Throwable e) {
            mLoginView.hideProgressLoader();
            mLoginView.displayLoginError(e.getMessage());
        }
    }

    private class CreateAccountSubscriber extends DefaultSubscriber<SignUpCredentials>{
        @Override
        public void onNext(SignUpCredentials signUpCredentials) {
            mLoginView.hideProgressLoader();
            mLoginView.onLoginSuccess( signUpCredentials );
        }
        @Override
        public void onError(Throwable e) {
            mLoginView.hideProgressLoader();
            mLoginView.displayLoginError(e.getMessage());
        }
    }

}
