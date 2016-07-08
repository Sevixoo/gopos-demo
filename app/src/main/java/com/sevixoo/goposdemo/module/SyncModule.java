package com.sevixoo.goposdemo.module;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;

import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.data.repository.CategoriesRepository;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;
import com.sevixoo.goposdemo.domain.service.IAccountManager;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.service.auth.impl.AccountManager;
import com.sevixoo.goposdemo.service.rest.IGoPOSWebService;
import com.sevixoo.goposdemo.service.sync.ISyncDispatcher;
import com.sevixoo.goposdemo.service.sync.ISyncHelper;
import com.sevixoo.goposdemo.service.sync.ISyncReceiver;
import com.sevixoo.goposdemo.service.sync.impl.CategorySyncAdapter;
import com.sevixoo.goposdemo.service.sync.impl.SyncBroadcastDispatcher;
import com.sevixoo.goposdemo.service.sync.impl.SyncBroadcastReceiver;
import com.sevixoo.goposdemo.service.sync.impl.SyncHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-07.
 */
@Module
public class SyncModule {

    Context         mContext;
    String          mAuthority;

    public SyncModule(Context context, String mAuthority ) {
        mContext = context;
        this.mAuthority = mAuthority;
    }

    @Provides
    public AbstractThreadedSyncAdapter provideCategorySyncAdapter(ICategoriesRepository repository , AccountConfig mAccountConfig, IAccountManager mAccountManager, IGoPOSWebService GoPOSWebService, ISyncDispatcher mSyncDispatcher){
        return new CategorySyncAdapter(  mContext , repository ,mAccountConfig,mAccountManager,GoPOSWebService, mSyncDispatcher , mAuthority);
    }

    @Provides
    public ISyncHelper provideSyncHelper(IAccountManager accountManager){
        return new SyncHelper( accountManager.getLoggedAccount() , mAuthority );
    }

    @Provides
    public ISyncReceiver provideSyncReceiver( ){
        return new SyncBroadcastReceiver(mAuthority);
    }

    @Provides
    public ISyncDispatcher provideSyncDispatcher( ){
        return new SyncBroadcastDispatcher(mContext , mAuthority);
    }

}
