package io.github.ovoyo.mvpapp.data.db;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.ovoyo.mvpapp.data.db.model.DaoMaster;
import io.github.ovoyo.mvpapp.di.ApplicationContext;
import io.github.ovoyo.mvpapp.di.DatabaseInfo;

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
