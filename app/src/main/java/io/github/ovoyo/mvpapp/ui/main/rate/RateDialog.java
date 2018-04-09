package io.github.ovoyo.mvpapp.ui.main.rate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.di.component.ActivityComponent;
import io.github.ovoyo.mvpapp.ui.base.dialog.BaseDialog;
import io.github.ovoyo.mvpapp.utils.AppUtils;


public class RateDialog extends BaseDialog implements RateDialogMVPView {

    private static final String TAG = "RateDialog";

    @Inject
    RateDialogMVPPresenter<RateDialogMVPView> mPresenter;

    @BindView(R.id.rating_bar_feedback)
    RatingBar mRatingBar;

    @BindView(R.id.et_message)
    EditText mMessage;

    @BindView(R.id.view_rating_message)
    View mRatingMessageView;

    @BindView(R.id.view_play_store_rating)
    View mPlayStoreRatingView;

    @BindView(R.id.btn_submit)
    Button mSubmitButton;

    public static RateDialog get() {
        return new RateDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }

        return view;
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @OnClick(R.id.btn_later)
    void onLaterClick() {
        mPresenter.onLaterClicked();
    }

    @OnClick(R.id.btn_rate_on_play_store)
    void onPlayStoreRateClick() {
        mPresenter.onPlayStoreRatingClicked();
    }

    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String message = "";
        Editable editable = mMessage.getText();
        if (editable != null) {
            message = editable.toString();
        }
        mPresenter.onRatingSubmitted(mRatingBar.getRating(), message);
    }

    @Override
    public void openPlayStoreForRating() {
        AppUtils.openPlayStoreForApp(getBaseActivity());
    }

    @Override
    public void showPlayStoreRatingView() {
        mPlayStoreRatingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRatingMessageView() {
        mRatingMessageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSubmitButton() {
        mSubmitButton.setVisibility(View.GONE);
    }

    @Override
    public void disableRatingStars() {
        mRatingBar.setIsIndicator(false);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

    @Override
    public void setup(View view) {

    }
}
