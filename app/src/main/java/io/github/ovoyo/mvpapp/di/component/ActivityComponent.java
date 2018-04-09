package io.github.ovoyo.mvpapp.di.component;

import dagger.Component;
import io.github.ovoyo.mvpapp.di.PerActivity;
import io.github.ovoyo.mvpapp.di.module.ActivityModule;
import io.github.ovoyo.mvpapp.ui.about.AboutFragment;
import io.github.ovoyo.mvpapp.ui.login.LoginActivity;
import io.github.ovoyo.mvpapp.ui.main.MainActivity;
import io.github.ovoyo.mvpapp.ui.main.rate.RateDialog;
import io.github.ovoyo.mvpapp.ui.splash.SplashActivity;

@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(RateDialog dialog);

    void inject(AboutFragment fragment);

}
