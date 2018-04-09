package io.github.ovoyo.mvpapp.ui.feed.opensource;

import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;


public interface OpenSourceMVPPresenter<V extends OpenSourceMVPView> extends MVPPresenter<V> {

    void onViewPrepared();

}
