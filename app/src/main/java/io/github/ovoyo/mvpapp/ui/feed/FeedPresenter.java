package io.github.ovoyo.mvpapp.ui.feed;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;


public class FeedPresenter<V extends FeedMVPView> extends BasePresenter<V> implements FeedMVPPresenter<V> {

    @Inject
    public FeedPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }
}
