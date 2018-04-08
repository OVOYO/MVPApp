package io.github.ovoyo.mvpapp.di.component;

import dagger.Component;
import io.github.ovoyo.mvpapp.di.PerService;
import io.github.ovoyo.mvpapp.di.module.ServiceModule;
import io.github.ovoyo.mvpapp.service.SyncService;

@PerService
@Component(dependencies = AppComponent.class,modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService syncService);

}
