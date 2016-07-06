package com.sevixoo.goposdemo.service.auth;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IAccountManager {

    Observable<String> createAccount(SignUpCredentials credentials);

    Observable<String> updateCredentials(SignUpCredentials credentials);

}
