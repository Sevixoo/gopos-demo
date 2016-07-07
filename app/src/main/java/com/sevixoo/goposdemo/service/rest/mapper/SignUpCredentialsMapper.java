package com.sevixoo.goposdemo.service.rest.mapper;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.service.rest.pojo.SuccessAuthorizationResponse;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class SignUpCredentialsMapper {

    public SignUpCredentials transform( SuccessAuthorizationResponse object ){
        SignUpCredentials signUpCredentials = new SignUpCredentials();
        signUpCredentials.authToken = object.access_token;
        signUpCredentials.tokenType = object.token_type;
        return signUpCredentials;
    }

}
