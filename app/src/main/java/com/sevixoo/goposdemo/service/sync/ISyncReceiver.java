package com.sevixoo.goposdemo.service.sync;

import android.app.Activity;

/**
 * Created by Seweryn on 2016-07-08.
 */
public interface ISyncReceiver {

    public interface SyncListener{
        public void onSyncStarted();
        public void onSyncFinish();
        public void onSyncError( String message );
    }

    public void setSyncListener( SyncListener listener );
    public void onPause(Activity activity);
    public void onResume(Activity activity);
}
