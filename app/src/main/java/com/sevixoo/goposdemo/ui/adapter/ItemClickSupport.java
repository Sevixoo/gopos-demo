package com.sevixoo.goposdemo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sevixoo.goposdemo.R;

/**
 * Created by Seweryn on 2016-07-07.
 */
public class ItemClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;

    private int res_id = - 1;

    private void setId( int id  ){
        res_id = id;
    }

    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //int pos = Integer.parseInt( v.getTag().toString() );
                int pos = mRecyclerView.getChildAdapterPosition( v );
                mOnItemClickListener.onItemClicked( pos , v );
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                //int pos = Integer.parseInt( v.getTag().toString() );
                int pos = mRecyclerView.getChildAdapterPosition( v );
                return mOnItemLongClickListener.onItemLongClicked( pos , v );
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                if(res_id==-1) {
                    view.setOnClickListener(mOnClickListener);
                }else{
                    view.findViewById(res_id).setOnClickListener(mOnClickListener);
                }
            }
            if (mOnItemLongClickListener != null) {
                if(res_id==-1) {
                    view.setOnLongClickListener(mOnLongClickListener);
                }else{
                    view.findViewById(res_id).setOnLongClickListener(mOnLongClickListener);
                }
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    private ItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
        res_id = -1;
    }

    public static ItemClickSupport addTo(RecyclerView view, int res_id) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupport(view);
            support.setId(res_id);
        }
        return support;
    }
    public static ItemClickSupport addTo(RecyclerView view ) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }


    public static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    public interface OnItemClickListener {

        void onItemClicked(int position, View v);
    }

    public interface OnItemLongClickListener {

        boolean onItemLongClicked(int position, View v);
    }
}
