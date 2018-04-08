package io.github.ovoyo.mvpapp.ui.login;


import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;

@PerActivity
public interface LoginMVPPresenter<V extends LoginMVPView> extends MVPPresenter<V> {

    void onServerLoginClick(String email,String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}
