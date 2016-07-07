package com.sevixoo.goposdemo.service.rest.pojo;

import java.io.Serializable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class SuccessAuthorizationResponse implements Serializable {

    public String access_token;
    public String token_type;
    public String refresh_token;
    public int expires_in;
    public String scope;

    public SuccessAuthorizationResponse( ) {
        /* */
    }
}
