package io.github.ovoyo.mvpapp.ui.main;

import android.text.TextUtils;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainPresenter<V extends MainMVPView> extends BasePresenter<V> implements MainMVPPresenter<V> {

    @Inject
    public MainPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onDrawerOptionAboutClick() {
        getMVPView().closeNavigationDrawer();
        getMVPView().showAboutFragment();
    }

    @Override
    public void onDrawerOptionLogoutClick() {
        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(logoutResponse -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            getDataManager().setUserAsLoggedOut();
                            getMVPView().hideLoading();
                            getMVPView().openLoginActivity();
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
    public void onDrawerRateUsClick() {
        getMVPView().closeNavigationDrawer();
        getMVPView().showRateUsDialog();
    }

    @Override
    public void onDrawerMyFeedClick() {
        getMVPView().closeNavigationDrawer();
        getMVPView().openMyFeedActivity();
    }

    @Override
    public void onViewInitialized() {

        Disposable disposable = getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(questionList -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (null != questionList && !questionList.isEmpty()) {
                        getMVPView().refreshQuestionnaire(questionList);
                    }
                });
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void onCardExhausted() {
        Disposable disposable = getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(questionList -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (null != questionList && !questionList.isEmpty()) {
                        getMVPView().reloadQuestionnaire(questionList);
                    }
                });
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void onNavMenuCreated() {

        if (!isViewAttached()) {
            return;
        }
        getMVPView().updateAppVersion();

        String curUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(curUserName)) {
            getMVPView().updateUserName(curUserName);
        }

        String curEmail = getDataManager().getCurrentUserEmail();
        if (!TextUtils.isEmpty(curEmail)) {
            getMVPView().updateUserEmail(curEmail);
        }

        String curPic = getDataManager().getCurrentUserProfilePicUrl();
        if (!TextUtils.isEmpty(curPic)) {
            getMVPView().updateUserProfilePic(curPic);
        }
    }
}
