package com.sevixoo.goposdemo.domain.interactor;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IAuthenticateService {

    Observable<SignUpCredentials> userSignIn(String accountName, String password, String authTokenType);

}
