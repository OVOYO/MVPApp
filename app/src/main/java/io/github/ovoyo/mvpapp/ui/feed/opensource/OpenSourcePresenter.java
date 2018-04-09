package io.github.ovoyo.mvpapp.ui.feed.opensource;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class OpenSourcePresenter<V extends OpenSourceMVPView> extends BasePresenter<V> implements OpenSourceMVPPresenter<V> {

    @Inject
    public OpenSourcePresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onViewPrepared() {
        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .getOpenSourceApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(openSourceResponse -> {
                            if (openSourceResponse != null && openSourceResponse.getData() != null) {
                                getMVPView().updateRepo(openSourceResponse.getData());
                            }
                            getMVPView().hideLoading();
                        },
                        throwable -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (throwable instanceof ANError) {
                                ANError error = (ANError) throwable;
                                handleApiError(error);
                            }
                        });
        getCompositeDisposable().add(disposable);
    }
}
