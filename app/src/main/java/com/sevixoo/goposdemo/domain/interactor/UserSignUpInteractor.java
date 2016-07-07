package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.service.IAuthenticateService;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class UserSignUpInteractor extends Interactor {

    private IAuthenticateService    mAuthenticateService;
    private String                  mLogin;
    private String                  mPassword;
    private String                  mTokenType;
    private String                  mAccountType;

    public UserSignUpInteractor( IAuthenticateService authenticateService, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mAuthenticateService = authenticateService;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mAuthenticateService.userSignIn(mLogin,mPassword,mTokenType,mAccountType);
    }

    public void execute(String login , String password , String tokenType, String accountType, Subscriber UseCaseSubscriber) {
        mLogin = login;
        mPassword = password;
        mTokenType = tokenType;
        mAccountType = accountType;
        super.execute(UseCaseSubscriber);
    }
}
