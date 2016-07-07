package com.sevixoo.goposdemo.module;

import android.accounts.AbstractAccountAuthenticator;
import android.content.Context;

import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.domain.executor.PostExecutionThread;
import com.sevixoo.goposdemo.domain.executor.ThreadExecutor;
import com.sevixoo.goposdemo.domain.interactor.CheckAuthorizationInteractor;
import com.sevixoo.goposdemo.domain.interactor.CreateAccountInteractor;
import com.sevixoo.goposdemo.domain.interactor.UserSignUpInteractor;
import com.sevixoo.goposdemo.domain.service.IAccountManager;
import com.sevixoo.goposdemo.domain.service.IAuthenticateService;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.service.auth.impl.AccountManager;
import com.sevixoo.goposdemo.service.auth.impl.GoPOSAuthenticator;
import com.sevixoo.goposdemo.service.auth.impl.AuthenticateService;
import com.sevixoo.goposdemo.service.rest.IGoPOSWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Seweryn on 2016-07-06.
 */
@Module
public class AuthModule {

    private Context                     mContext;
    private String                      mClientSecret;
    private String                      mClientID;

    public AuthModule(Context context) {
        mContext = context;
        this.mClientSecret = context.getString( R.string.config_api_client_sacred );
        this.mClientID = context.getString( R.string.config_api_client_id );
    }

    @Provides
    @Singleton
    public android.accounts.AccountManager provideAndroidAccountManager(){
        return android.accounts.AccountManager.get(mContext);
    }

    @Provides
    @Singleton
    public AccountConfig provideAccountConfig(){
        return new AccountConfig( mContext );
    }

    @Provides
    @Singleton
    public IAccountManager provideAccountManager( android.accounts.AccountManager manager ,  AccountConfig config ){
        return new AccountManager( manager , config );
    }

    @Provides
    public AbstractAccountAuthenticator provideAuthenticator( AccountConfig accountConfig, IAuthenticateService authenticateService ){
        return new GoPOSAuthenticator(mContext,accountConfig,authenticateService);
    }

    @Provides
    @Singleton
    public IAuthenticateService providesAuthenticateService(IGoPOSWebService goPOSWebService ) {
        return new AuthenticateService( goPOSWebService , mClientSecret , mClientID );
    }

    @Provides
    public CreateAccountInteractor providesCreateAccountInteractor(IAccountManager accountManager , ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new CreateAccountInteractor( accountManager, threadExecutor , postExecutionThread );
    }

    @Provides
    public UserSignUpInteractor providesUserSignUpInteractor(IAuthenticateService authenticateService,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new UserSignUpInteractor(authenticateService, threadExecutor , postExecutionThread );
    }

    @Provides
    public CheckAuthorizationInteractor providesCheckAuthorizationInteractor(IAccountManager accountManager ,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        return new CheckAuthorizationInteractor(accountManager , threadExecutor , postExecutionThread );
    }

}
