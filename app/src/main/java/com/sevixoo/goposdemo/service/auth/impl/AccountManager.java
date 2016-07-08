package com.sevixoo.goposdemo.service.auth.impl;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Build;
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
    public Observable<String> destroyAccount(final String accountName) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                try {
                    Account account = getAccountByName(accountName);
                    if(account==null) {
                        subscriber.onError( new AccountManagerException("Account not found") );
                    }

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                        AccountManagerFuture<Bundle> future =mAccountManager.removeAccount(account , null, null , null );
                        future.getResult();
                    }else{
                        AccountManagerFuture<Boolean> future = mAccountManager.removeAccount(account, null , null );
                        future.getResult();
                    }
                    subscriber.onNext("");
                    subscriber.onCompleted();

                } catch (Exception e) {
                    subscriber.onError( new AccountManagerException(e.getCause()) );
                }
            }
        });
    }

    private Account getAccountByName(String accountName){
        Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
        for(Account account : accounts){
            if(account.name.equals(accountName)){
                return account;
            }
        }
        return null;
    }

    @Override
    public Observable<SignUpCredentials> getUserData(String accountName) {
        return null;
    }

    @Override
    public String getLoggedAccountName() {
        Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
        if(accounts!=null&&accounts.length>0){
            return accounts[0].name;
        }
        return null;
    }

    @Override
    public Account getLoggedAccount() {
        Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
        if(accounts!=null&&accounts.length>0){
            return accounts[0];
        }
        return null;
    }

    @Override
    public String getAuthTokenBlocking(String authTokenType)throws Exception {
        Account[] accounts = mAccountManager.getAccountsByType( mAccountConfig.getAccountType() );
        Account account = accounts[0];
        final AccountManagerFuture<Bundle> future =
                mAccountManager.getAuthToken(account, authTokenType, null, true, null, null);

        Bundle bnd = future.getResult();
        final String authtoken = bnd.getString(android.accounts.AccountManager.KEY_AUTHTOKEN);
        if(TextUtils.isEmpty(authtoken)){
            return null;
        }else {
            return authtoken;
        }
    }


}
