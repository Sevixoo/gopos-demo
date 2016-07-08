package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sevixoo.goposdemo.data.GoPOSDatabaseHelper;
import com.sevixoo.goposdemo.data.repository.CategoriesRepository;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.interactor.LoadCategoriesDetailsInteractor;
import com.sevixoo.goposdemo.domain.interactor.LoadCategoriesInteractor;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-08.
 */
@Module
public class DataModule {

    public Context mContext;
    private GoPOSDatabaseHelper mGoPOSDatabaseHelper;


    public DataModule(Context mContext) {
        this.mContext = mContext;
        mGoPOSDatabaseHelper = OpenHelperManager.getHelper(mContext, GoPOSDatabaseHelper.class);
    }

    @Provides
    ICategoriesRepository provideCategoriesRepository(GoPOSDatabaseHelper goPOSDatabaseHelper){
        return new  CategoriesRepository(goPOSDatabaseHelper);
    }

    @Provides
    GoPOSDatabaseHelper provideGoPOSDatabaseHelper(){
        return mGoPOSDatabaseHelper;
    }

    @Provides
    LoadCategoriesInteractor provideLoadCategoriesInteractor(ICategoriesRepository mCategoriesRepository , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread ){
        return  new LoadCategoriesInteractor(mCategoriesRepository,threadExecutor,postExecutionThread);
    }

    @Provides
    LoadCategoriesDetailsInteractor provideLoadCategoriesDetailsInteractor(ICategoriesRepository mCategoriesRepository , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread ){
        return  new LoadCategoriesDetailsInteractor(mCategoriesRepository,threadExecutor,postExecutionThread);
    }



}
