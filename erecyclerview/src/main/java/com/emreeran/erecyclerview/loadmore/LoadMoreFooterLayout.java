package com.emreeran.erecyclerview.loadmore;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Base class for load more views, all views used as load more items must extend this class
 * Created by Emre Eran on 11/12/15.
 */
public class LoadMoreFooterLayout extends FrameLayout implements LoadMoreInterface {

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreFooterLayout(Context context) {
        super(context);
    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onLoad() {
        mOnLoadMoreListener.onLoadMore();
    }

    @Override
    public void onComplete() {

    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }
}
