package io.github.ovoyo.mvpapp.ui.main.rate;


import io.github.ovoyo.mvpapp.ui.base.MVPPresenter;

public interface RateDialogMVPPresenter<V extends RateDialogMVPView> extends MVPPresenter<V> {

    void onRatingSubmitted(float rating, String message);

    void onCancelClicked();

    void onLaterClicked();

    void onPlayStoreRatingClicked();

}
