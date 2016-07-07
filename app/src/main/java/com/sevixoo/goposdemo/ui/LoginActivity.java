package com.sevixoo.goposdemo.ui;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sevixoo.goposdemo.GoPOSApplication;
import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.component.LoginComponent;
import com.sevixoo.goposdemo.domain.entity.SignUpCredentials;
import com.sevixoo.goposdemo.ui.presenter.ILoginPresenter;
import com.sevixoo.goposdemo.ui.view.ILoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Seweryn on 2016-07-06.
 */
public class LoginActivity extends AccountAuthenticatorActivity implements ILoginView {

    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";
    public final static String PARAM_USER_PASS = "USER_PASS";

    public static Intent getAuthorizationIntent(Context context,String mAccountType,String mAuthTokenType){
        Intent intent = new Intent(context,LoginActivity.class);
        Bundle extra = new Bundle();
        extra.putString( ARG_ACCOUNT_TYPE , mAccountType );
        extra.putString( ARG_AUTH_TYPE , mAuthTokenType );
        extra.putBoolean( ARG_IS_ADDING_NEW_ACCOUNT , true );
        intent.putExtras(extra);
        return intent;
    }

    public static ILoginPresenter   mLoginPresenter;

    private String                  mAuthTokenType;

    @BindView(R.id.accountName)
    public EditText                 mAccountNameEditText;

    @BindView(R.id.accountPassword)
    public EditText                 mAccountPasswordEditText;

    private String                  mAccountType;
    private ProgressDialog          mProgressDialog;
    private boolean                 mIsAddingAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        String accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
        mAccountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);
        mIsAddingAccount = getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false);

        //tests
        mAccountNameEditText.setText( "test@gmail.com" );
        mAccountPasswordEditText.setText( "test2016" );

        if (accountName != null) {
            mAccountNameEditText.setText(accountName);
        }

        LoginComponent component = GoPOSApplication.get(this).getLoginComponent( mIsAddingAccount);
        //component.inject(this);

        if( mLoginPresenter == null ){
            mLoginPresenter = component.getLoginPresenter();
        }

        mLoginPresenter.setView( this );

    }

    @OnClick(R.id.submit)
    public void onClickButtonSubmit(View v) {
        String userName = mAccountNameEditText.getText().toString();
        String userPass = mAccountPasswordEditText.getText().toString();
        mLoginPresenter.onUserSignIn( userName , userPass , mAuthTokenType );
    }

    @Override
    public void onLoginSuccess( SignUpCredentials signUpCredentials ) {
        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, signUpCredentials.login);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccountType);
        data.putString(AccountManager.KEY_AUTHTOKEN, signUpCredentials.authToken);
        data.putString(PARAM_USER_PASS, signUpCredentials.password);

        Intent intent = new Intent();
        intent.putExtras(data);

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
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
        toast.show();
    }

    @Override
    public void hideProgressLoader() {
        if(mProgressDialog!=null){
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}
