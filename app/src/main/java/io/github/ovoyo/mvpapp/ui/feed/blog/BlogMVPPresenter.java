package io.github.ovoyo.mvpapp.ui.feed.blog;

import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;


public interface BlogMVPPresenter<V extends BlogMVPView> extends MVPPresenter<V> {

    void onViewPrepared();

}
