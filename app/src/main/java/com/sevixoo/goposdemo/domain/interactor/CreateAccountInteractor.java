package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.service.IAccountManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class CreateAccountInteractor extends Interactor {

    IAccountManager mAccountManager;
    boolean         mCanAddAccount;
    SignUpCredentials mCredentials;

    public CreateAccountInteractor(IAccountManager mAccountManager, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mAccountManager = mAccountManager;

    }

    @Override
    protected Observable buildUseCaseObservable() {
        if(mCanAddAccount){
            return mAccountManager.createAccount( mCredentials );
        }else{
            return mAccountManager.updateCredentials( mCredentials );
        }
    }

    public void execute(boolean canAddAccount, SignUpCredentials credentials, Subscriber UseCaseSubscriber) {
        mCanAddAccount = canAddAccount;
        mCredentials = credentials;
        super.execute(UseCaseSubscriber);
    }
}
