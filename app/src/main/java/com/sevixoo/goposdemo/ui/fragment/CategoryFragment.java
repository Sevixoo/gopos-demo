package com.sevixoo.goposdemo.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sevixoo.goposdemo.GoPOSApplication;
import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.domain.entity.CategoryItem;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.sevixoo.goposdemo.ui.presenter.ICategoryDetailsPresenter;
import com.sevixoo.goposdemo.ui.view.ICategoryDetailsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment implements ICategoryDetailsView{


    public interface OnFragmentInteractionListener {

    }

    private static final String ARG_CATEGORY_ID = "param1";

    private long mCategoryID;

    private static ICategoryDetailsPresenter mCategoryDetailsPresenter;

    private OnFragmentInteractionListener mListener;

    public CategoryFragment() {
        setRetainInstance(true);
    }

    @BindView(R.id.label)
    public TextView mLabel;

    public static CategoryFragment newInstance(long param1) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CATEGORY_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoryID = getArguments().getLong(ARG_CATEGORY_ID);
        }
        if( mCategoryDetailsPresenter == null ){
            mCategoryDetailsPresenter = GoPOSApplication.get(getContext()).getCategoryListComponent().getCategoryDetailsPresenter();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_category_fragmet, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCategoryDetailsPresenter.setView(this);
        mCategoryDetailsPresenter.loadCategoryDetails(mCategoryID);
    }


    @Override
    public void onResume() {
        super.onResume();
        mCategoryDetailsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCategoryDetailsPresenter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCategoryDetailsPresenter.setView(null);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()  + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void displayCategory(CategoryListItem item) {
        mLabel.setText( item.getName() );
    }

    @Override
    public void displayError(String message) {
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();
    }

}
