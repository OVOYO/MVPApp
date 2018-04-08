package io.github.ovoyo.mvpapp.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.ui.base.BaseActivity;
import io.github.ovoyo.mvpapp.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginMVPView{

    @Inject
    LoginPresenter<LoginMVPView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPWEditText;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getComponent().inject(this);

        ButterKnife.bind(this);

        mPresenter.onAttach(LoginActivity.this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(){
        mPresenter.onServerLoginClick(mEmailEditText.getText().toString(),mPWEditText.getText().toString());
    }

    @OnClick(R.id.ib_google_login)
    void onGoogleLoginClick(){
        mPresenter.onGoogleLoginClick();
    }

    @OnClick(R.id.ib_fb_login)
    void onFacebookLoginClick(){
        mPresenter.onFacebookLoginClick();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }
}
