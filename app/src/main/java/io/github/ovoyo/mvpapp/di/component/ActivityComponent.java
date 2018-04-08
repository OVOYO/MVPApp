package io.github.ovoyo.mvpapp.di.component;

import dagger.Component;
import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.di.module.ActivityModule;
import io.github.ovoyo.mvpapp.ui.MainActivity;

@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
