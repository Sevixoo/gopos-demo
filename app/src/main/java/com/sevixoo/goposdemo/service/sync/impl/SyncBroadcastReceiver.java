package com.sevixoo.goposdemo.service.sync.impl;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.sevixoo.goposdemo.service.sync.ISyncReceiver;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class SyncBroadcastReceiver extends BroadcastReceiver implements ISyncReceiver {

    public final String mAuthority;
    public SyncListener mSyncListener;

    public SyncBroadcastReceiver(String mAuthority) {
        this.mAuthority = mAuthority;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(mSyncListener==null)return;

        if(intent.getAction().equals(mAuthority+".STARTED")){
            mSyncListener.onSyncStarted();
        }else if(intent.getAction().equals(mAuthority+".FINISH")){
            mSyncListener.onSyncFinish();
        }else if(intent.getAction().equals(mAuthority+".ERROR")){
            Bundle bundle = intent.getExtras();
            mSyncListener.onSyncError( bundle.getString(SyncBroadcastDispatcher.ARG_MESSAGE) );
        }
    }

    public void setSyncListener( SyncListener listener ){
        mSyncListener = listener;
    }

    @Override
    public void onPause(Activity activity) {
        activity.unregisterReceiver(this);
    }

    @Override
    public void onResume(Activity activity) {
        IntentFilter filter = new IntentFilter();
        filter.addAction( mAuthority+".STARTED" );
        filter.addAction( mAuthority+".FINISH" );
        filter.addAction( mAuthority+".ERROR" );
        activity.registerReceiver(this,filter);
    }


}
