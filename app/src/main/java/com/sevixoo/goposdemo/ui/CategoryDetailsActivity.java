package com.sevixoo.goposdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sevixoo.goposdemo.R;

public class CategoryDetailsActivity extends AppCompatActivity {

    public static final String ARG_CATEGORY_ID = "category_id";

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
    }
}
