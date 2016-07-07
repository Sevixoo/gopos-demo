package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.service.IAccountManager;
import com.sevixoo.goposdemo.service.auth.impl.AccountManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class CheckAuthorizationInteractor extends Interactor {

    public IAccountManager      mAccountManager;
    public String               mAuthTokenType;

    public CheckAuthorizationInteractor(IAccountManager accountManager , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mAccountManager = accountManager;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mAccountManager.getAuthToken(mAuthTokenType);
    }

    public void execute( String authTokenType , Subscriber UseCaseSubscriber) {
        mAuthTokenType = authTokenType;
        super.execute(UseCaseSubscriber);
    }
}
