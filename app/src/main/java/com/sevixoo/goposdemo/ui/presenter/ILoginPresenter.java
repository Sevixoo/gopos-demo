package com.sevixoo.goposdemo.ui.presenter;

import com.sevixoo.goposdemo.ui.view.ILoginView;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface ILoginPresenter extends IBasicPresenter {

    void onUserSignIn( String login , String password , String tokenType );
    void setView(ILoginView view);
}
