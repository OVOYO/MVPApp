package io.github.ovoyo.mvpapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.ovoyo.mvpapp.BuildConfig;
import io.github.ovoyo.mvpapp.R;
import io.github.ovoyo.mvpapp.data.db.model.Question;
import io.github.ovoyo.mvpapp.ui.about.AboutFragment;
import io.github.ovoyo.mvpapp.ui.base.BaseActivity;
import io.github.ovoyo.mvpapp.ui.feed.FeedActivity;
import io.github.ovoyo.mvpapp.ui.login.LoginActivity;
import io.github.ovoyo.mvpapp.ui.main.qcard.QuestionCard;
import io.github.ovoyo.mvpapp.ui.main.rate.RateDialog;
import io.github.ovoyo.mvpapp.utils.ScreenUtils;

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

    @BindView(R.id.cards_container)
    SwipePlaceHolderView mCardsContainerView;

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
        mCircleImageView = headerLayout.findViewById(R.id.iv_user_pic);
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

        int sWidth = ScreenUtils.getScreenWidth(this);
        int sHeight = ScreenUtils.getScreenHeight(this);

        mCardsContainerView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10F)
                .setWidthSwipeDistFactor(5F)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth((int) (0.90 * sWidth))
                        .setViewHeight((int) (0.75 * sHeight))
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f));
        mCardsContainerView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (count == 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(() -> mPresenter.onCardExhausted(), 800);
                }
            }
        });
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
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.get(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void refreshQuestionnaire(List<Question> questionList) {
        if (questionList != null && !questionList.isEmpty()) {
            for (Question q : questionList) {
                if (q != null && q.getOptionList() != null && q.getOptionList().size() == 3) {
                    mCardsContainerView.addView(new QuestionCard(q));
                }
            }
        }
    }

    @Override
    public void reloadQuestionnaire(List<Question> questionList) {
        refreshQuestionnaire(questionList);
        ScaleAnimation animation =
                new ScaleAnimation(
                        1.15f, 1, 1.15f, 1,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

        mCardsContainerView.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
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
        Glide
                .with(this)
                .load("https://pic3.zhimg.com/80/v2-e2ae9159cf260da0ae61951bf1926abe.jpg")
                .asBitmap()
                .into(mCircleImageView);
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTV.setText(version);
    }

    @Override
    public void showRateUsDialog() {
        RateDialog.get().show(getSupportFragmentManager());
    }

    @Override
    public void openMyFeedActivity() {
        startActivity(FeedActivity.getStartIntent(this));
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
