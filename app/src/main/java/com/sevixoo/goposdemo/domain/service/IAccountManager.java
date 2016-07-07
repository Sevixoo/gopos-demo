package com.sevixoo.goposdemo.domain.service;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IAccountManager {

    Observable<SignUpCredentials> createAccount(SignUpCredentials credentials);

    Observable<SignUpCredentials> updateCredentials(SignUpCredentials credentials);

    Observable<String> getAuthToken(final String authTokenType);

    Observable<String> invalidateAuthToken(final String authTokenType);

    Observable<String> destroyAccount( String accountName );

    Observable<SignUpCredentials> getUserData( String accountName );

    String getLoggedAccountName();

}
