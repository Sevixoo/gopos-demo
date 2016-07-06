package com.sevixoo.goposdemo.service.auth;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IAuthenticateService {

    String userSignIn( String accountName, String password, String authTokenType );

}
