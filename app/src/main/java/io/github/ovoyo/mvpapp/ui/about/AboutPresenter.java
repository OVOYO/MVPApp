package io.github.ovoyo.mvpapp.ui.about;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;


public class AboutPresenter<V extends AboutMVPView> extends BasePresenter<V> implements AboutMVPPresenter<V> {

    @Inject
    public AboutPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }


}
