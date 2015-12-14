package com.emreeran.erecyclerview.loadmore;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.emreeran.erecyclerview.R;

/**
 * Created by Emre Eran on 12/12/15.
 */
public class DefaultLoadMoreFooter extends LoadMoreFooterLayout {

    public DefaultLoadMoreFooter(Context context) {
        super(context);
    }

    @Override
    public void onPrepare() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_default_load_more, this, false);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        setLayoutTransition(new LayoutTransition());
        setLayoutParams(lp);
        addView(view);
        setVisibility(INVISIBLE);
        super.onPrepare();
    }

    @Override
    public void onLoad() {
        setVisibility(VISIBLE);
        super.onLoad();
    }

    @Override
    public void onComplete() {
        setVisibility(INVISIBLE);
        super.onComplete();
    }
}
