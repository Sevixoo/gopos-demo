package com.sevixoo.goposdemo.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.domain.service.IAuthenticateService;
import com.sevixoo.goposdemo.service.auth.impl.AuthenticateService;
import com.sevixoo.goposdemo.service.rest.IGoPOSWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Seweryn on 2016-07-06.
 */

@Module
public class RESTApiModule {

    private String              mBaseUrl;

    public RESTApiModule(Application context) {
        this.mBaseUrl = context.getString( R.string.config_api_base_url );
    }

    @Provides
    @Singleton
    IGoPOSWebService providesAuthenticateService( Retrofit retrofit ) {
        return retrofit.create( IGoPOSWebService.class );
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

}
