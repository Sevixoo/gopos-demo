package com.sevixoo.goposdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Seweryn on 2016-07-07.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void onBindItem( T item );
}
