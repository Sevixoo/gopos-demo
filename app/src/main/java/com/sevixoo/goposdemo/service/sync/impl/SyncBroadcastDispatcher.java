package com.sevixoo.goposdemo.service.sync.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sevixoo.goposdemo.service.sync.ISyncDispatcher;

/**
 * Created by Seweryn on 2016-07-08.
 */
public class SyncBroadcastDispatcher implements ISyncDispatcher {

    public static final String ARG_MESSAGE = "message";

    private Context mContext;
    private String mAuthority;

    public SyncBroadcastDispatcher(Context mContext,String authority) {
        this.mContext = mContext;
        this.mAuthority = authority;
    }

    @Override
    public void sendSyncStarted() {
        Intent intent = new Intent(mAuthority+".STARTED");
        mContext.sendBroadcast(intent);
    }

    @Override
    public void sendSyncFinish() {
        Intent intent = new Intent(mAuthority+".FINISH");
        mContext.sendBroadcast(intent);
    }

    @Override
    public void sendSyncError( String message ){
        Intent intent = new Intent(mAuthority+".ERROR");
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE,message);
        intent.putExtras(bundle);
        mContext.sendBroadcast(intent);

    }
}
