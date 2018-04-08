package io.github.ovoyo.mvpapp.ui.login;

import android.text.TextUtils;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.data.network.model.LoginRequest;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.CommonUtils;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class LoginPresenter<V extends LoginMVPView> extends BasePresenter<V> implements LoginMVPPresenter<V> {

    @Inject
    public LoginPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            getMVPView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMVPView().onError(R.string.invalid_email);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getMVPView().onError(R.string.empty_password);
            return;
        }

        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                            getDataManager().updateUserInfo(
                                    loginResponse.getAccessToken(),
                                    loginResponse.getUserId(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    loginResponse.getUserName(),
                                    loginResponse.getUserEmail(),
                                    loginResponse.getServerProfilePicUrl()
                            );

                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();
                            getMVPView().openMainActivity();
                        },
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();

                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        });

        getCompositeDisposable().add(disposable);
    }

    @Override
    public void onGoogleLoginClick() {
        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .doGoogleLoginApiCall(new LoginRequest.GoogleLoginRequest("test1", "test1"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                            getDataManager().updateUserInfo(
                                    loginResponse.getAccessToken(),
                                    loginResponse.getUserId(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                                    loginResponse.getUserName(),
                                    loginResponse.getUserEmail(),
                                    loginResponse.getGoogleProfilePicUrl()
                            );

                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();
                            getMVPView().openMainActivity();
                        },
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();

                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        });
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void onFacebookLoginClick() {
        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .doFacebookLoginApiCall(new LoginRequest.FacebookLoginRequest("test3", "test4"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(loginResponse -> {
                            getDataManager().updateUserInfo(
                                    loginResponse.getAccessToken(),
                                    loginResponse.getUserId(),
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                                    loginResponse.getUserName(),
                                    loginResponse.getUserEmail(),
                                    loginResponse.getFbProfilePicUrl()
                            );

                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();
                            getMVPView().openMainActivity();
                        },
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();

                            if (throwable instanceof ANError) {
                                ANError anError = (ANError) throwable;
                                handleApiError(anError);
                            }
                        });
        getCompositeDisposable().add(disposable);
    }
}
