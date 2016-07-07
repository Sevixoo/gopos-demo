package com.sevixoo.goposdemo.service.rest;

import com.sevixoo.goposdemo.service.rest.pojo.SuccessAuthorizationResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Seweryn on 2016-07-06.
 */
public interface IGoPOSWebService {

    @GET("/oauth/token")
    public Call<SuccessAuthorizationResponse> authorization(@QueryMap Map<String, String> options );

    @GET("/api/sale/venue/6/productCategory")
    public Call<String> listCategories(@Query("sort") String access_token );

}