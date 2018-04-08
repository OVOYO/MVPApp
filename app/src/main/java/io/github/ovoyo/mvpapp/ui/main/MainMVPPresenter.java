package io.github.ovoyo.mvpapp.ui.main;

import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;

@PerActivity
public interface MainMVPPresenter<V extends MainMVPView> extends MVPPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerRateUsClick();

    void onDrawerMyFeedClick();

    void onViewInitialized();

    void onCardExhausted();

    void onNavMenuCreated();

}
