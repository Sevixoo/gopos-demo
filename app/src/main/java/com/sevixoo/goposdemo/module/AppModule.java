package com.sevixoo.goposdemo.module;

import android.app.Application;

import com.sevixoo.goposdemo.ui.executor.AsyncExecutor;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.ui.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application providesApplication() {
        return mApplication;
    }

    @Provides
    public ThreadExecutor providesThreadExecutor(){
        return new AsyncExecutor();
    }

    @Provides
    public PostExecutionThread providesPostExecutionThread(){
        return new UIThread();
    }

}