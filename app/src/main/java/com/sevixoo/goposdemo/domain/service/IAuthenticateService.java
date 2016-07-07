package com.sevixoo.goposdemo.domain.service;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.exceptions.RESTApiException;

import java.io.IOException;

import rx.Observable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IAuthenticateService {
    Observable<SignUpCredentials> userSignIn(String accountName, String password, String authTokenType,String accountType);
    SignUpCredentials userSignInBlocking(String accountName, String password, String authTokenType,String accountType)throws IOException;
}
