package com.sevixoo.goposdemo.ui.view;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface ILoginView {

    void onLoginSuccess(SignUpCredentials signUpCredentials);

    void showProgressLoader();

    void displayLoginError( String error );

    void hideProgressLoader();

}
