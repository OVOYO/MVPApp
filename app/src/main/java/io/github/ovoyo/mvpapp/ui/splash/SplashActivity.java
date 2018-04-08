package io.github.ovoyo.mvpapp.ui.splash;

import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.ui.base.BaseActivity;
import io.github.ovoyo.mvpapp.ui.login.LoginActivity;
import io.github.ovoyo.mvpapp.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashMVPView {

    @Inject
    SplashMVPPresenter<SplashMVPView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void startSyncService() {

    }
}
