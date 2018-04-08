package io.github.ovoyo.mvpapp.ui.splash;


import io.github.ovoyo.mvpapp.ui.base.MVPView;

public interface SplashMVPView extends MVPView {

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();

}
