package com.sevixoo.goposdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.ui.fragment.CategoryFragment;

public class CategoryDetailsActivity extends AppCompatActivity implements CategoryFragment.OnFragmentInteractionListener{

    public static final String ARG_CATEGORY_ID = "category_id";
    public static final String INSTANCE_STATE_PARAM_CATEGORY_ID = "category_id";

    long mCategoryRemoteID;

    public static Intent createIntent(Context context , long categoryId ){
        Intent intent = new Intent( context ,CategoryDetailsActivity.class );
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_CATEGORY_ID , categoryId);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        initializeActivity(savedInstanceState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mCategoryRemoteID = getIntent().getLongExtra(ARG_CATEGORY_ID, -1);
            addFragment(R.id.fragmentContainer, CategoryFragment.newInstance(mCategoryRemoteID) );
        } else {
            mCategoryRemoteID = savedInstanceState.getLong(INSTANCE_STATE_PARAM_CATEGORY_ID);
        }
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

}
