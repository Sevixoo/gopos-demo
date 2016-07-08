package com.sevixoo.goposdemo.service.sync;

import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.Intent;
import android.os.IBinder;

import com.sevixoo.goposdemo.GoPOSApplication;

import javax.inject.Inject;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoriesSyncService extends Service {

    private static AbstractThreadedSyncAdapter sSyncAdapter = null;

    private static final Object sSyncAdapterLock = new Object();

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = GoPOSApplication.get(this).getSyncCategoriesComponent().getCategorySyncAdapter();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
