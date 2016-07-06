package com.sevixoo.goposdemo.service.auth;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class AuthenticatorHelper {

    private Context mContext;

    public AuthenticatorHelper(Context context) {
        this.mContext = context;
    }

    /*public void getAuthToken(  ){
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();

        am.getAuthToken(
                myAccount_,                     // Account retrieved using getAccountsByType()
                "Manage your tasks",            // Auth scope
                options,                        // Authenticator-specific options
                this,                           // Your activity
                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                new Handler(new OnError()));    // Callback called if an error occurs
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            // Get the result of the operation from the AccountManagerFuture.
            Bundle bundle = result.getResult();

            // The token is a named value in the bundle. The name of the value
            // is stored in the constant AccountManager.KEY_AUTHTOKEN.
            token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
        }
    }*/

}
