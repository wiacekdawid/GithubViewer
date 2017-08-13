package com.wiacek.githubviewer;

import android.content.Context;
import android.support.multidex.*;

import com.squareup.leakcanary.LeakCanary;
import com.wiacek.githubviewer.di.components.AppComponent;
import com.wiacek.githubviewer.di.components.DaggerAppComponent;
import com.wiacek.githubviewer.di.modules.AppModule;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubViewerApplication extends MultiDexApplication {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDI();
        initializeLeakCanary();
    }

    public static GithubViewerApplication getGithubViewerApplication(Context context) {
        return (GithubViewerApplication) context.getApplicationContext();
    }

    private void initializeDI() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }
    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
