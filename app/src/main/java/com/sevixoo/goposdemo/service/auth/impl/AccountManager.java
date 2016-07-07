package com.sevixoo.goposdemo.service.auth.impl;

import android.accounts.Account;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.exceptions.AccountManagerException;
import com.sevixoo.goposdemo.domain.service.IAccountManager;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class AccountManager implements IAccountManager {

    private android.accounts.AccountManager mAccountManager;
    private AccountConfig                   mAccountConfig;

    public AccountManager(android.accounts.AccountManager mAccountManager,AccountConfig config) {
        this.mAccountManager = mAccountManager;
        mAccountConfig = config;
    }

    @Override
    public Observable<SignUpCredentials> createAccount(final SignUpCredentials credentials) {
        return Observable.create(new Observable.OnSubscribe<SignUpCredentials>() {
            @Override
            public void call(Subscriber<? super SignUpCredentials> subscriber) {
                try {
                    Log.e( "createAccount" , "createAccount" );
                    Log.e( "createAccount" , "accountType" + credentials.accountType );
                    Log.e( "createAccount" , "login" + credentials.login );
                    Account account = new Account(credentials.login, credentials.accountType );
                    boolean ret = mAccountManager.addAccountExplicitly(account, credentials.password, null);
                    if(!ret){
                        subscriber.onError( new AccountManagerException("Error adding account") );
                    }else {
                        mAccountManager.setAuthToken(account, credentials.tokenType, credentials.authToken);
                        subscriber.onNext(credentials);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError( new AccountManagerException(e.getCause()) );
                }
            }
        });
    }

    @Override
    public Observable<SignUpCredentials> updateCredentials(final SignUpCredentials credentials) {
        return Observable.create(new Observable.OnSubscribe<SignUpCredentials>() {
            @Override
            public void call(Subscriber<? super SignUpCredentials> subscriber) {
                try {
                    Account account = new Account(credentials.login, credentials.accountType );
                    mAccountManager.setPassword(account, credentials.password);
                    subscriber.onNext(credentials);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError( new AccountManagerException(e.getCause()) );
                }
            }
        });
    }

    @Override
    public Observable<String> getAuthToken( final String authTokenType ) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
                    Account account = accounts[0];
                    final AccountManagerFuture<Bundle> future =
                            mAccountManager.getAuthToken(account, authTokenType, null, true, null, null);

                    Bundle bnd = future.getResult();
                    final String authtoken = bnd.getString(android.accounts.AccountManager.KEY_AUTHTOKEN);
                    if(TextUtils.isEmpty(authtoken)){
                        subscriber.onError( new AccountManagerException() );
                    }else {
                        Log.d( "AccountManager" , "getAuthToken().authtoken=" + authtoken );
                        subscriber.onNext(authtoken);
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError( new AccountManagerException(e.getCause()) );
                }
            }
        });
    }

    @Override
    public Observable<String> invalidateAuthToken( final String authTokenType ) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
                    Account account = accounts[0];
                    final AccountManagerFuture<Bundle> future =
                            mAccountManager.getAuthToken(account, authTokenType, null, true, null, null);

                    Bundle bnd = future.getResult();
                    final String authtoken = bnd.getString(android.accounts.AccountManager.KEY_AUTHTOKEN);
                    mAccountManager.invalidateAuthToken(account.type, authtoken);
                    subscriber.onNext(authtoken);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError( new AccountManagerException(e.getCause()) );
                }
            }
        });
    }

    @Override
    public Observable<String> destroyAccount(String accountName) {
        return null;
    }

    @Override
    public Observable<SignUpCredentials> getUserData(String accountName) {
        return null;
    }

}
