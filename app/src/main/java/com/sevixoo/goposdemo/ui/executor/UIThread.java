package com.sevixoo.goposdemo.ui.executor;

import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class UIThread implements PostExecutionThread {

    public UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
