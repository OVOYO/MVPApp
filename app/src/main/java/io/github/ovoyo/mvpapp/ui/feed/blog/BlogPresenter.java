package io.github.ovoyo.mvpapp.ui.feed.blog;

import com.androidnetworking.error.ANError;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BlogPresenter<V extends BlogMVPView> extends BasePresenter<V> implements BlogMVPPresenter<V> {

    @Inject
    public BlogPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onViewPrepared() {
        getMVPView().showLoading();

        Disposable disposable = getDataManager()
                .getBlogApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                            if (blogResponse != null && blogResponse.getData() != null) {
                                getMVPView().updateBlog(blogResponse.getData());
                            }
                            getMVPView().hideLoading();
                        },
                        throwable -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getMVPView().hideLoading();

                            if (throwable instanceof ANError) {
                                ANError error = (ANError) throwable;
                                handleApiError(error);
                            }
                        });
        getCompositeDisposable().add(disposable);
    }
}
