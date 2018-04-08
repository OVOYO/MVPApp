package io.github.ovoyo.mvpapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovoyo.mvpapp.BuildConfig;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.db.model.Question;
import io.github.ovoyo.mvpapp.ui.base.BaseActivity;
import io.github.ovoyo.mvpapp.ui.login.LoginActivity;

public class MainActivity extends BaseActivity implements MainMVPView {

    @Inject
    MainPresenter<MainMVPView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTV;

    private CircleImageView mCircleImageView;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setup();
    }

    private void setup() {

        setSupportActionBar(mToolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        setupNavMenu();
        mPresenter.onNavMenuCreated();

        setupCardContainerView();
        mPresenter.onViewInitialized();
    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mCircleImageView = headerLayout.findViewById(R.id.iv_pic);
        mNameTextView = headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.nav_item_about:
                            mPresenter.onDrawerOptionAboutClick();
                            return true;
                        case R.id.nav_item_rate_us:
                            mPresenter.onDrawerRateUsClick();
                            return true;
                        case R.id.nav_item_feed:
                            mPresenter.onDrawerMyFeedClick();
                            return true;
                        case R.id.nav_item_logout:
                            mPresenter.onDrawerOptionLogoutClick();
                            return true;
                        default:
                            return false;
                    }
                });
    }

    private void setupCardContainerView() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(MainActivity.this));
        finish();
    }

    @Override
    public void showAboutFragment() {

    }

    @Override
    public void refreshQuestionnaire(List<Question> questionList) {

    }

    @Override
    public void reloadQuestionnaire(List<Question> questionList) {

    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {

    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTV.setText(version);
    }

    @Override
    public void showRateUsDialog() {

    }

    @Override
    public void openMyFeedActivity() {

    }

    @Override
    public void closeNavigationDrawer() {
        mDrawer.closeDrawer(Gravity.START);
    }

    @Override
    public void lockDrawer() {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
}
