package io.github.ovoyo.mvpapp.ui.about;

import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;

@PerActivity
public interface AboutMVPPresenter<V extends AboutMVPView> extends MVPPresenter<V> {
}
