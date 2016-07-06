package com.sevixoo.goposdemo.ui.presenter;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface ILoginPresenter extends IBasicPresenter {

    void onUserSignIn( String login , String password , String tokenType );

}
