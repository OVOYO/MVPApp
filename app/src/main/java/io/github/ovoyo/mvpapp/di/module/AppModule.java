package io.github.ovoyo.mvpapp.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.ovoyo.mvpapp.BuildConfig;
import io.github.ovoyo.mvpapp.data.AppDataManager;
import io.github.ovoyo.mvpapp.data.DataManager;
import io.github.ovoyo.mvpapp.data.db.AppDbHelper;
import io.github.ovoyo.mvpapp.data.db.DbHelper;
import io.github.ovoyo.mvpapp.data.network.ApiHelper;
import io.github.ovoyo.mvpapp.data.network.AppApiHelper;
import io.github.ovoyo.mvpapp.data.network.model.ApiHeader;
import io.github.ovoyo.mvpapp.data.prefs.AppPreferencesHelper;
import io.github.ovoyo.mvpapp.data.prefs.PreferencesHelper;
import io.github.ovoyo.mvpapp.di.ApiInfo;
import io.github.ovoyo.mvpapp.di.ApplicationContext;
import io.github.ovoyo.mvpapp.di.DatabaseInfo;
import io.github.ovoyo.mvpapp.utils.AppConstants;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(apiKey, preferencesHelper.getCurrentUserId(), preferencesHelper.getAccessToken());
    }

}
