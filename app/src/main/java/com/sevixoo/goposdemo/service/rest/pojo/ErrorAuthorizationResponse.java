package com.sevixoo.goposdemo.service.rest.pojo;

import java.io.Serializable;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class ErrorAuthorizationResponse implements Serializable {

    public String error;
    public String error_description;

    public ErrorAuthorizationResponse( ) {
        /* */
    }
}
