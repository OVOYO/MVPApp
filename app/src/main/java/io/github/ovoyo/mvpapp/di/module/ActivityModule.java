package io.github.ovoyo.mvpapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.github.ovoyo.mvpapp.di.ActivityContext;
import io.github.ovoyo.mvpapp.di.PerActivity;
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
}
