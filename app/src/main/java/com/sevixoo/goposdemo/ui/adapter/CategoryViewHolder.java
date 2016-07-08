package com.sevixoo.goposdemo.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sevixoo.goposdemo.R;
import com.sevixoo.goposdemo.domain.entity.CategoryListItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class CategoryViewHolder extends BaseViewHolder<CategoryListItem> {

    @BindView(R.id.imageView)
    public ImageView            mImageView;
    @BindView(R.id.text_view_name)
    public TextView             mTitleView;
    private View                mBody;

    private String              mBaseUrl;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mBody = itemView;
        mBaseUrl = itemView.getContext().getString(R.string.config_api_base_url);
    }

    @Override
    public void onBindItem(CategoryListItem item) {
        mTitleView.setText(item.getName());
        Log.e("getImagePath" , mBaseUrl + item.getImagePath());
        Picasso.with(mImageView.getContext())
                .load( mBaseUrl + "/" + item.getImagePath() )
                .placeholder(R.drawable.photo_placeholder)
                .into(mImageView);
    }
}
