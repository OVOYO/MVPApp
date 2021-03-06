package io.github.ovoyo.mvpapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.github.ovoyo.mvpapp.data.network.model.BlogResponse;
import io.github.ovoyo.mvpapp.data.network.model.OpenSourceResponse;
import io.github.ovoyo.mvpapp.di.ActivityContext;
import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.ui.about.AboutMVPPresenter;
import io.github.ovoyo.mvpapp.ui.about.AboutMVPView;
import io.github.ovoyo.mvpapp.ui.about.AboutPresenter;
import io.github.ovoyo.mvpapp.ui.feed.FeedMVPPresenter;
import io.github.ovoyo.mvpapp.ui.feed.FeedMVPView;
import io.github.ovoyo.mvpapp.ui.feed.FeedPagerAdapter;
import io.github.ovoyo.mvpapp.ui.feed.FeedPresenter;
import io.github.ovoyo.mvpapp.ui.feed.blog.BlogAdapter;
import io.github.ovoyo.mvpapp.ui.feed.blog.BlogMVPPresenter;
import io.github.ovoyo.mvpapp.ui.feed.blog.BlogMVPView;
import io.github.ovoyo.mvpapp.ui.feed.blog.BlogPresenter;
import io.github.ovoyo.mvpapp.ui.feed.opensource.OpenSourceAdapter;
import io.github.ovoyo.mvpapp.ui.feed.opensource.OpenSourceMVPPresenter;
import io.github.ovoyo.mvpapp.ui.feed.opensource.OpenSourceMVPView;
import io.github.ovoyo.mvpapp.ui.feed.opensource.OpenSourcePresenter;
import io.github.ovoyo.mvpapp.ui.login.LoginMVPPresenter;
import io.github.ovoyo.mvpapp.ui.login.LoginMVPView;
import io.github.ovoyo.mvpapp.ui.login.LoginPresenter;
import io.github.ovoyo.mvpapp.ui.main.MainMVPPresenter;
import io.github.ovoyo.mvpapp.ui.main.MainMVPView;
import io.github.ovoyo.mvpapp.ui.main.MainPresenter;
import io.github.ovoyo.mvpapp.ui.main.rate.RateDialogMVPPresenter;
import io.github.ovoyo.mvpapp.ui.main.rate.RateDialogMVPView;
import io.github.ovoyo.mvpapp.ui.main.rate.RateDialogPresenter;
import io.github.ovoyo.mvpapp.ui.splash.SplashMVPPresenter;
import io.github.ovoyo.mvpapp.ui.splash.SplashMVPView;
import io.github.ovoyo.mvpapp.ui.splash.SplashPresenter;
import io.github.ovoyo.mvpapp.utils.rx.AppSchedulerProvider;
import io.github.ovoyo.mvpapp.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mAppCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return mAppCompatActivity;
    }

    @Provides
    AppCompatActivity provideActivity(){
        return mAppCompatActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider(){
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMVPPresenter<SplashMVPView> provideSplashPresenter(SplashPresenter<SplashMVPView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMVPPresenter<LoginMVPView> provideLoginPresenter(LoginPresenter<LoginMVPView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    MainMVPPresenter<MainMVPView> provideMainPresenter(MainPresenter<MainMVPView> presenter){
        return presenter;
    }

    @Provides
    RateDialogMVPPresenter<RateDialogMVPView> provideRateDialogPresenter(RateDialogPresenter<RateDialogMVPView> presenter){
        return presenter;
    }

    @Provides
    AboutMVPPresenter<AboutMVPView> provideAboutPresenter(AboutPresenter<AboutMVPView> presenter){
        return presenter;
    }

    @Provides
    FeedMVPPresenter<FeedMVPView> provideFeedPresenter(FeedPresenter<FeedMVPView> presenter){
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPageAdapter(AppCompatActivity activity){
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    BlogMVPPresenter<BlogMVPView> provideBlogPresenter(BlogPresenter<BlogMVPView> presenter){
        return presenter;
    }

    @Provides
    BlogAdapter provideBlogAdapter(){
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>(0));
    }

    @Provides
    OpenSourceMVPPresenter<OpenSourceMVPView> provideOpenSourcePresenter(OpenSourcePresenter<OpenSourceMVPView> presenter){
        return presenter;
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter(){
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>(0));
    }
}
