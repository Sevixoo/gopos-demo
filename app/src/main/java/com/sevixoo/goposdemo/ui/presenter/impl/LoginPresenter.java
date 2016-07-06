package com.sevixoo.goposdemo.ui.presenter.impl;

import com.sevixoo.goposdemo.domain.DefaultSubscriber;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.interactor.UserSignUpInteractor;
import com.sevixoo.goposdemo.service.auth.IAuthenticateService;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.view.ILoginView;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoginPresenter implements ILoginPresenter {

    private ILoginView          mLoginView;
    private UserSignUpInteractor mUserSignUpInteractor;

    public LoginPresenter(ILoginView loginView, UserSignUpInteractor userSignUpInteractor) {
        this.mLoginView = loginView;
        this.mUserSignUpInteractor = userSignUpInteractor;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onUserSignIn(String login, String password, String tokenType) {
        mUserSignUpInteractor.execute( new LoginSubscriber() );
    }

    private class LoginSubscriber extends DefaultSubscriber<SignUpCredentials>{
        @Override
        public void onNext(SignUpCredentials s) {
        }
        @Override
        public void onCompleted() {
        }
        @Override
        public void onError(Throwable e) {
        }
    }

}
