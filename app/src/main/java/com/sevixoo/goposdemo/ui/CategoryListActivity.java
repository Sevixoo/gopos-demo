package com.sevixoo.goposdemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sevixoo.goposdemo.GoPOSApplication;
import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.component.CategoryListComponent;
import com.sevixoo.goposdemo.component.SyncComponent;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.service.auth.impl.AccountConfig;
import com.sevixoo.goposdemo.service.sync.ISyncHelper;
import com.sevixoo.goposdemo.service.sync.ISyncReceiver;
import com.sevixoo.goposdemo.ui.adapter.CategoriesRecyclerAdapter;
import com.sevixoo.goposdemo.ui.adapter.EndlessRecyclerViewScrollListener;
import com.sevixoo.goposdemo.ui.adapter.EndlessScrollListener;
import com.sevixoo.goposdemo.ui.adapter.ItemClickSupport;
import com.sevixoo.goposdemo.ui.presenter.ICategoryPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListActivity extends AppCompatActivity implements ISyncReceiver.SyncListener , ICategoryView , ItemClickSupport.OnItemClickListener{

    private final int REQ_SIGN_IN = 1;

    public static ICategoryPresenter mCategoryPresenter;

    @Inject
    public AccountConfig                mAccountConfig;
    private ProgressDialog              mProgressDialog;

    @BindView(R.id.recycler_view)
    public  RecyclerView                mRecyclerView;
    @Inject
    public  CategoriesRecyclerAdapter   mAdapter;
    private LinearLayoutManager         mLayoutManager;

    @BindView(R.id.swiperefresh)
    public SwipeRefreshLayout           mSwipeRefreshLayout;

    @BindView(R.id.empty_view)
    public FrameLayout                  mEmptyView;

    public ISyncHelper                  mSyncHelper;
    public ISyncReceiver                mSyncReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);

        CategoryListComponent component = GoPOSApplication.get(this).getCategoryListComponent();
        SyncComponent sync = GoPOSApplication.get(this).getSyncCategoriesComponent();
        component.inject(this);

        mSyncHelper = sync.getSyncHelper();
        mSyncReceiver = sync.getSyncReceiver();

        mLayoutManager = new LinearLayoutManager(this);

        if(mCategoryPresenter==null){
            mCategoryPresenter = component.getCategoryPresenter();
        }

        mCategoryPresenter.setView(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new CategoryEndlessScrollListener(mLayoutManager));
        ItemClickSupport.addTo( mRecyclerView ).setOnItemClickListener(this);

        mSyncReceiver.setSyncListener(this);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSyncHelper.requestSync();
            }
        });

        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSyncHelper.requestSync();
            }
        });

        if(savedInstanceState==null){
            mCategoryPresenter.checkLogin();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categoty_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mCategoryPresenter.onClickLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQ_SIGN_IN){
            if(resultCode==RESULT_CANCELED){
                finish();
            }else{
                mCategoryPresenter.checkLogin();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void showProgressLoader() {
        if(mProgressDialog==null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.show();
    }

    @Override
    public void displayError(String error) {
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
    public void onItemsLoaded(List<CategoryListItem> listItems) {
        mAdapter.pushItems( listItems );
        if(mAdapter.getItemCount()==0){
            mEmptyView.setVisibility(View.VISIBLE);
        }else{
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoginSuccess() {
        if(mAdapter.getItemCount()==0) {
            mCategoryPresenter.loadCategories(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCategoryPresenter.onResume();
        mSyncReceiver.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCategoryPresenter.onPause();
        mSyncReceiver.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCategoryPresenter.onDestroy();
    }

    @Override
    public void onItemClicked(int position, View v) {
        CategoryListItem item = mAdapter.getItem(position);
        Intent intent = CategoryDetailsActivity.createIntent(this,item.getID());
        startActivity(intent);
    }

    @Override
    public void onSyncStarted() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onSyncFinish() {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter.clear();
        mCategoryPresenter.loadCategories(0);
    }

    @Override
    public void onSyncError(String message) {
        mSwipeRefreshLayout.setRefreshing(false);
        displayError( message );
    }

    private class CategoryEndlessScrollListener extends EndlessRecyclerViewScrollListener{
        public CategoryEndlessScrollListener(LinearLayoutManager layoutManager) {
            super(layoutManager);
        }
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
            mCategoryPresenter.loadCategories( totalItemsCount );
        }
    }

}
