package com.sevixoo.goposdemo.service.sync.impl;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.sevixoo.goposdemo.data.repository.CategoriesRepository;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.exceptions.RESTApiException;
import com.sevixoo.goposdemo.domain.repository.ICategoriesRepository;
import com.sevixoo.goposdemo.domain.service.IAccountManager;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.service.rest.IGoPOSWebService;
import com.sevixoo.goposdemo.service.rest.pojo.CategoryItemsResponse;
import com.sevixoo.goposdemo.service.sync.ISyncDispatcher;

import java.sql.SQLException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategorySyncAdapter extends AbstractThreadedSyncAdapter {

    private String                  mCategoriesAuthority;
    private ISyncDispatcher         mSyncDispatcher;
    private IGoPOSWebService        mGoPOSWebService;
    private IAccountManager         mAccountManager;
    private AccountConfig           mAccountConfig;
    private ICategoriesRepository   mCategoriesRepository;

    public CategorySyncAdapter(Context context , ICategoriesRepository repository , AccountConfig mAccountConfig, IAccountManager mAccountManager, IGoPOSWebService GoPOSWebService, ISyncDispatcher mSyncDispatcher , String categoriesAuthority) {
        super(context, true);
        mCategoriesAuthority = categoriesAuthority;
        this.mSyncDispatcher = mSyncDispatcher;
        mGoPOSWebService = GoPOSWebService;
        this.mAccountConfig = mAccountConfig;
        this.mAccountManager = mAccountManager;
        mCategoriesRepository = repository;
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        mSyncDispatcher.sendSyncStarted();

        try {
            Thread.sleep(2000);

            String authToken =  mAccountManager.getAuthTokenBlocking( mAccountConfig.getCategoryTokenType() );
            if(TextUtils.isEmpty(authToken)){
                syncResult.stats.numAuthExceptions = 1;
                throw new Exception("Unauthorized");
            }
            Response<CategoryItemsResponse> response = mGoPOSWebService.listCategories( authToken ).execute();
            Log.e("Response" , response.toString() );

            if(response.isSuccessful()){
                CategoryItemsResponse resp = response.body();
                for( CategoryItem categoryItem : resp.items ){
                    Log.e( "CategoryItem" , categoryItem.getName() );
                    mCategoriesRepository.insert(categoryItem);
                    syncResult.stats.numInserts++;
                }
            }else{
                throw new RESTApiException( response.message() );
            }

            mSyncDispatcher.sendSyncFinish();
        }catch (SQLException ex){
            ex.printStackTrace();
            syncResult.databaseError = true;
            mSyncDispatcher.sendSyncError("SQLException");
        }catch (Exception ex){
            ex.printStackTrace();
            syncResult.stats.numIoExceptions = 1;
            mSyncDispatcher.sendSyncError(ex.getMessage());
        }

        Log.d("CategorySyncAdapter","syncResult="+ syncResult.toDebugString());
    }
}
