package io.github.ovoyo.mvpapp;

import android.app.Application;

import javax.inject.Inject;

import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.di.component.AppComponent;
import io.github.ovoyo.mvpapp.di.component.DaggerAppComponent;
import io.github.ovoyo.mvpapp.di.module.AppModule;

public class MVPApp extends Application {

    @Inject
    DataManager mDataManager;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
