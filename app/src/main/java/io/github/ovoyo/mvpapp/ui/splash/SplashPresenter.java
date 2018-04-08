package io.github.ovoyo.mvpapp.ui.splash;


import javax.inject.Inject;

import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SplashPresenter<V extends SplashMVPView> extends BasePresenter<V> implements SplashMVPPresenter<V> {

    @Inject
    public SplashPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMVPView().startSyncService();

        Disposable disposable = getDataManager()
                .seedDatabaseQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .concatMap(aBoolean -> getDataManager().seedDatabaseOptions())
                .subscribe(aBoolean -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            decideNextActivity();
                        },
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().onError(R.string.some_error);

                            decideNextActivity();
                        });

        getCompositeDisposable().add(disposable);
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMVPView().openLoginActivity();
        } else {
            getMVPView().openMainActivity();
        }
    }
}
