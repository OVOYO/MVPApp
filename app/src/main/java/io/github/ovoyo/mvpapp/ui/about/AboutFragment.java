package io.github.ovoyo.mvpapp.ui.about;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.di.component.ActivityComponent;
import io.github.ovoyo.mvpapp.ui.base.BaseFragment;

public class AboutFragment extends BaseFragment implements AboutMVPView {

    public static final String TAG = "AboutFragment";

    @Inject
    AboutMVPPresenter<AboutMVPView> mPresenter;

    public static AboutFragment get(){
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void setup(View view) {

    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
