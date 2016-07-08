package com.sevixoo.goposdemo.service.sync.impl;

import android.accounts.Account;
import android.content.ContentResolver;
import android.os.Bundle;

import com.sevixoo.goposdemo.service.sync.ISyncHelper;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class SyncHelper implements ISyncHelper {

    Account         mAccount;
    String          mAuthority;

    public SyncHelper(Account mAccount , String mAuthority) {
        this.mAccount = mAccount;
        this.mAuthority = mAuthority;
    }

    @Override
    public void requestSync() {
        Bundle data = new Bundle();
        data.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        data.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync( mAccount,  mAuthority,data );
    }

    @Override
    public boolean isSyncPending() {
        return ContentResolver.isSyncPending(  mAccount,  mAuthority );
    }

    @Override
    public boolean isSyncActive() {
        return ContentResolver.isSyncActive(  mAccount,  mAuthority );
    }
}
