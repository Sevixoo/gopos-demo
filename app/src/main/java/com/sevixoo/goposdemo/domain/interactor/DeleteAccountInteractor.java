package com.sevixoo.goposdemo.domain.interactor;

import android.util.Log;

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
public class DeleteAccountInteractor extends Interactor {

    IAccountManager     mAccountManager;

    public DeleteAccountInteractor(IAccountManager mAccountManager, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mAccountManager = mAccountManager;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mAccountManager.destroyAccount( mAccountManager.getLoggedAccountName() );
    }
}
