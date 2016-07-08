package com.sevixoo.goposdemo.component;

import android.content.AbstractThreadedSyncAdapter;

import com.sevixoo.goposdemo.module.AppModule;
import com.sevixoo.goposdemo.module.AuthModule;
import com.sevixoo.goposdemo.module.DataModule;
import com.sevixoo.goposdemo.module.LoginModule;
import com.sevixoo.goposdemo.module.RESTApiModule;
import com.sevixoo.goposdemo.module.SyncModule;
import com.sevixoo.goposdemo.service.sync.CategoriesSyncService;
import com.sevixoo.goposdemo.service.sync.ISyncDispatcher;
import com.sevixoo.goposdemo.service.sync.ISyncHelper;
import com.sevixoo.goposdemo.service.sync.ISyncReceiver;
import com.sevixoo.goposdemo.ui.CategoryListActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Seweryn on 2016-07-07.
 */

@Singleton
@Component( modules={ AppModule.class, RESTApiModule.class , AuthModule.class , SyncModule.class , DataModule.class} )
public interface SyncComponent {
    AbstractThreadedSyncAdapter getCategorySyncAdapter( );
    ISyncHelper getSyncHelper();
    ISyncReceiver getSyncReceiver();
    ISyncDispatcher getSyncDispatcher();
}
