package com.emreeran.erecyclerview.pulltorefresh;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import com.emreeran.erecyclerview.SwipeLayout;

/**
 * Base class for refresh views, all views used as pull to refresh items must extend this class
 * Created by Emre Eran on 10/12/15.
 */
public class SwipeRefreshHeaderLayout extends SwipeLayout implements SwipeInterface {

    protected OnRefreshListener mOnRefreshListener;

    public SwipeRefreshHeaderLayout(Context context) {
        super(context);
    }

    @Override
    public void onRefresh() {
        setState(STATE_REFRESHING);
    }

    @Override
    public void onPrepare() {
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();
        mState = STATE_IDLE;
    }

    @Override
    public void onComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                smoothScrollTo(0);
                onReset();
            }
        }, 500);
    }

    @Override
    public void onSwipe(float delta) {
        if (mState != STATE_REFRESHING) {
            if (getVisibleHeight() > 0 || delta > 0) {
                setVisibleHeight((int) delta + getVisibleHeight());
                // Refresh header height is past refresh threshold, release will result on refresh
                if (mState == STATE_IDLE && getVisibleHeight() >= mMeasuredHeight) {
                    onReleaseToRefresh();
                    // Refresh header height is below refresh threshold, release will result on header view collapsing
                } else if (mState == STATE_RELEASE_TO_REFRESH && getVisibleHeight() < mMeasuredHeight) {
                    onReset();
                }
            }
        }
    }

    @Override
    public void onReleaseToRefresh() {
        setState(STATE_RELEASE_TO_REFRESH);
    }

    @Override
    public void onRelease() {
        int destHeight;
        // Check if is past refresh start threshold
        if (getVisibleHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            onRefresh();
            mOnRefreshListener.onRefresh();
        }

        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        } else {
            destHeight = 0;
        }
        smoothScrollTo(destHeight);
    }

    @Override
    public void onReset() {
        setState(STATE_IDLE);
    }

    public void setContainer(View view) {
        mContainer = (ViewGroup)view;
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }
}
