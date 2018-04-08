package io.github.ovoyo.mvpapp.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.github.ovoyo.mvpapp.MVPApp;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.di.ApplicationContext;
import io.github.ovoyo.mvpapp.di.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MVPApp mvpApp);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
