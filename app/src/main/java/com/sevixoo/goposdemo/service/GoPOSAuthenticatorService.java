package com.sevixoo.goposdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.sevixoo.goposdemo.service.auth.GoPOSAuthenticator;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class GoPOSAuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        GoPOSAuthenticator authenticator = new GoPOSAuthenticator(this);
        return authenticator.getIBinder();
    }
}
