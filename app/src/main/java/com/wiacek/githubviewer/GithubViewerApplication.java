package com.wiacek.githubviewer;

import android.support.multidex.*;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubViewerApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeLeakCanary();
    }

    private void initializeLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this) && BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
