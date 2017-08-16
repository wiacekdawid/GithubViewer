package com.wiacek.githubviewer;

import android.content.Context;
import android.support.multidex.*;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.wiacek.githubviewer.db.DbContants;
import com.wiacek.githubviewer.db.Migration;
import com.wiacek.githubviewer.db.ModelsRealmModule;
import com.wiacek.githubviewer.di.components.AppComponent;
import com.wiacek.githubviewer.di.components.DaggerAppComponent;
import com.wiacek.githubviewer.di.modules.AppModule;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubViewerApplication extends MultiDexApplication {

    private AppComponent appComponent;

    @Inject
    protected Migration migration;
    @Inject
    protected ModelsRealmModule modelsRealmModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
        initializeDb();
        initializeLeakCanary();
        initializeStetho();
    }

    public static GithubViewerApplication getGithubViewerApplication(Context context) {
        return (GithubViewerApplication) context.getApplicationContext();
    }

    private void initializeDI() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    private void initializeDb() {
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .migration(migration)
                .modules(modelsRealmModule)
                .schemaVersion(DbContants.REALM_SCHEMA_VERSION)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initializeStetho() {
        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                    .build())
                .build());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
