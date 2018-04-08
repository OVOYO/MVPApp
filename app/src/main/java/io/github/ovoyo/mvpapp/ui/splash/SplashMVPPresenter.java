package io.github.ovoyo.mvpapp.ui.splash;

import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;

@PerActivity
public interface SplashMVPPresenter<V extends SplashMVPView> extends MVPPresenter<V> {
}
