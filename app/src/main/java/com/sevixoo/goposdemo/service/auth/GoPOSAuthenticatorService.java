package com.sevixoo.goposdemo.service.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sevixoo.goposdemo.GoPOSApplication;

import javax.inject.Inject;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class GoPOSAuthenticatorService extends Service {

    @Inject
    AbstractAccountAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        GoPOSApplication.get(this).getAuthComponent().inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
