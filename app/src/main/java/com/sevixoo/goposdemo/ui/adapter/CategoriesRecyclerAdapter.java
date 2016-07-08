package com.sevixoo.goposdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    ArrayList<CategoryListItem> mDataList;

    public CategoriesRecyclerAdapter() {
        this.mDataList = new ArrayList<>();
    }

    public void pushItems( List<CategoryListItem> data ){
        mDataList.addAll(data);
        notifyItemRangeInserted( mDataList.size() - data.size()  , mDataList.size() );
    }

    public void clear(){
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        BaseViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        CategoryViewHolder myHolder = (CategoryViewHolder)holder;
        myHolder.onBindItem(getItem(position));
    }

    public CategoryListItem getItem(int position){
        return mDataList.get(position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
