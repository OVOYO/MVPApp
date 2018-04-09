package io.github.ovoyo.mvpapp.ui.main.rate;

import io.github.ovoyo.mvpapp.ui.base.dialog.DialogMVPView;


public interface RateDialogMVPView extends DialogMVPView {

    void openPlayStoreForRating();

    void showPlayStoreRatingView();

    void showRatingMessageView();

    void hideSubmitButton();

    void disableRatingStars();

    void dismissDialog();

}
