package com.emreeran.erecyclerview.pulltorefresh;

/**
 * Created by Emre Eran on 11/12/15.
 */
public interface SwipeInterface {
    void onPrepare();

    void onSwipe(float delta);

    void onReleaseToRefresh();

    void onRelease();

    void onRefresh();

    void onComplete();

    void onReset();

    int STATE_IDLE = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING = 2;
    int STATE_DONE = 3;
}
