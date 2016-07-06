package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class UserSignUpInteractor extends Interactor {

    private IAuthenticateService mAuthenticateService;
    private String mLogin;
    private String mPassword;
    private String mTokenType;

    public UserSignUpInteractor( String login , String password , String tokenType, IAuthenticateService authenticateService, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mAuthenticateService = authenticateService;
        mLogin = login;
        mPassword = password;
        mTokenType = tokenType;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mAuthenticateService.userSignIn(mLogin,mPassword,mTokenType);
    }
}
