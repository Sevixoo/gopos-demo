package com.sevixoo.goposdemo.service.auth.impl;

import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.domain.exceptions.RESTApiException;
import com.sevixoo.goposdemo.domain.service.IAuthenticateService;
import com.sevixoo.goposdemo.service.rest.IGoPOSWebService;
import com.sevixoo.goposdemo.service.rest.mapper.SignUpCredentialsMapper;
import com.sevixoo.goposdemo.service.rest.pojo.SuccessAuthorizationResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class AuthenticateService implements IAuthenticateService{

    private IGoPOSWebService mGoPOSWebService;

    private String              mClientSecret;
    private String              mClientID;

    public AuthenticateService(IGoPOSWebService goPOSWebService , String clientSecret , String clientID ) {
        mGoPOSWebService = goPOSWebService ;
        mClientID = clientID;
        mClientSecret = clientSecret;
    }

    @Override
    public Observable<SignUpCredentials> userSignIn(final String accountName,final String password, String authTokenType,final String accountType) {
        return Observable.create(new Observable.OnSubscribe<SignUpCredentials>() {
            @Override
            public void call(Subscriber<? super SignUpCredentials> subscriber) {
                try{
                    Map<String, String> params = new HashMap<String, String>();
                    params.put( "username" , accountName );
                    params.put( "password" , password );
                    params.put( "client_id" , mClientID );
                    params.put( "client_secret" , mClientSecret );
                    params.put( "grant_type" , "password" );

                    Response<SuccessAuthorizationResponse> resp = mGoPOSWebService.authorization( params ).execute();
                    if( resp.isSuccessful() ){
                        SuccessAuthorizationResponse response = resp.body();
                        SignUpCredentials credentials = new SignUpCredentialsMapper().transform( response );
                        credentials.login = accountName;
                        credentials.accountType = accountType;
                        credentials.password = password;
                        subscriber.onNext( credentials );
                        subscriber.onCompleted();
                    }else{
                        subscriber.onError(new RESTApiException( resp.message() ));
                    }
                }catch (Exception ex){
                    subscriber.onError(new RESTApiException(ex.getCause()));
                }
            }
        });

    }

    @Override
    public SignUpCredentials userSignInBlocking(String accountName, String password, String authTokenType, String accountType)throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put( "username" , accountName );
        params.put( "password" , password );
        params.put( "client_id" , mClientID );
        params.put( "client_secret" , mClientSecret );
        params.put( "grant_type" , "password" );

        Response<SuccessAuthorizationResponse> resp = mGoPOSWebService.authorization( params ).execute();
        if( resp.isSuccessful() ){
            SuccessAuthorizationResponse response = resp.body();
            SignUpCredentials credentials = new SignUpCredentialsMapper().transform( response );
            credentials.login = accountName;
            credentials.accountType = accountType;
            credentials.password = password;
            return credentials;
        }else{
            throw new IOException();
        }
    }
}
