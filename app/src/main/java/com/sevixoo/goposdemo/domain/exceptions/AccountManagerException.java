package com.sevixoo.goposdemo.domain.exceptions;

import java.io.IOException;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class AccountManagerException extends IOException {
    public AccountManagerException() {
    }

    public AccountManagerException(Throwable cause) {
        super(cause);
    }

    public AccountManagerException(String message) {
        super(message);
    }
}
