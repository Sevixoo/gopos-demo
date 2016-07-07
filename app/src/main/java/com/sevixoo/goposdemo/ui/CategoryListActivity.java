package com.sevixoo.goposdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sevixoo.goposdemo.GoPOSApplication;
import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class CategoryListActivity extends AppCompatActivity implements ICategoryView {

    private final int REQ_SIGN_IN = 1;

    @Inject
    public ICategoryPresenter mCategoryPresenter;

    @Inject
    public AccountConfig        mAccountConfig;

    private ProgressDialog      mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        GoPOSApplication.get(this).getCategoryListComponent(this).inject(this);

        if(savedInstanceState==null){
            mCategoryPresenter.checkLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgressLoader() {
        if(mProgressDialog==null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    @Override
    public void displayLoginError(String error) {
        Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }

    @Override
    public void hideProgressLoader() {
        if(mProgressDialog!=null){
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showScreenLoader() {

    }

    @Override
    public void hideScreenLoader() {

    }

    @Override
    public void showAuthorizationPage() {
        Intent intent = LoginActivity.getAuthorizationIntent(this,mAccountConfig.getAccountType(),mAccountConfig.getCategoryTokenType());
        startActivityForResult(intent,REQ_SIGN_IN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCategoryPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCategoryPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCategoryPresenter.onDestroy();
    }

}
