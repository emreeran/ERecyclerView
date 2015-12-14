package com.emreeran.erecyclerview.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emreeran.erecyclerview.R;

/**
 * Created by Emre Eran on 10/12/15.
 */
public class TwitterRefreshHeaderView extends SwipeRefreshHeaderLayout {

    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private Animation rotateUp;

    private Animation rotateDown;

    public TwitterRefreshHeaderView(Context context) {
        super(context);
    }

    @Override
    public void onPrepare() {
        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.layout_twitter_header, this, false);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        rotateUp = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_down);
        tvRefresh = (TextView) findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(GONE);
        ivArrow.setVisibility(GONE);

        super.onPrepare();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("REFRESHING");
    }

    @Override
    public void onSwipe(float y) {
        super.onSwipe(y);
        if (mState != STATE_REFRESHING) {
            ivArrow.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onReleaseToRefresh() {
        super.onReleaseToRefresh();
        ivArrow.clearAnimation();
        ivArrow.startAnimation(rotateUp);
        tvRefresh.setText("RELEASE TO REFRESH");
    }

    @Override
    public void onRelease() {
        super.onRelease();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("COMPLETE");
    }

    @Override
    public void onReset() {
        super.onReset();
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.startAnimation(rotateDown);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("SWIPE TO REFRESH");
    }

}
