package io.github.ovoyo.mvpapp.ui.main.rate;


import javax.inject.Inject;

import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.ui.base.BasePresenter;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class RateDialogPresenter<V extends RateDialogMVPView> extends BasePresenter<V> implements RateDialogMVPPresenter<V> {

    private static final String TAG = "RateDialogPresenter";

    private boolean mIsSecondaryActionShow = false;

    @Inject
    public RateDialogPresenter(CompositeDisposable compositeDisposable, DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(compositeDisposable, dataManager, schedulerProvider);
    }

    @Override
    public void onRatingSubmitted(float rating, String message) {

        if (rating == 0) {
            getMVPView().onError(R.string.rating_not_provided_error);
            return;
        }

        if (!mIsSecondaryActionShow) {
            if (rating == 5) {
                getMVPView().showPlayStoreRatingView();
                getMVPView().hideSubmitButton();
                getMVPView().disableRatingStars();
            } else {
                getMVPView().showRatingMessageView();
            }
            mIsSecondaryActionShow = true;
            return;
        }

        getMVPView().showLoading();

        getMVPView().hideLoading();
        getMVPView().showMessage(R.string.rating_thanks);
        getMVPView().dismissDialog();
    }

    @Override
    public void onCancelClicked() {
        getMVPView().dismissDialog();
    }

    @Override
    public void onLaterClicked() {
        getMVPView().dismissDialog();
    }

    @Override
    public void onPlayStoreRatingClicked() {
        getMVPView().openPlayStoreForRating();
        sendRatingDataToServerInBackground(5F);
        getMVPView().dismissDialog();
    }

    private void sendRatingDataToServerInBackground(float rating) {

    }
}
