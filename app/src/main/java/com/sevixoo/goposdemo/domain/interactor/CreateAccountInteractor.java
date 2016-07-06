package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.Interactor;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class CreateAccountInteractor extends Interactor {

    public CreateAccountInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
