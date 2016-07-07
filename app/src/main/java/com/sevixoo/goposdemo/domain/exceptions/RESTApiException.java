package com.sevixoo.goposdemo.domain.exceptions;

import java.io.IOException;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class RESTApiException extends IOException {
    public RESTApiException() {
    }

    public RESTApiException(Throwable cause) {
        super(cause);
    }

    public RESTApiException(String message) {
        super(message);
    }
}
