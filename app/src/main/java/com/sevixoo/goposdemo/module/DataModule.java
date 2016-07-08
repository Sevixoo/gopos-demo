package com.sevixoo.goposdemo.module;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.sevixoo.goposdemo.data.GoPOSDatabaseHelper;
import com.sevixoo.goposdemo.data.repository.CategoriesRepository;
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

}
