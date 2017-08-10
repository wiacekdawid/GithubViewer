package com.wiacek.githubviewer.di.modules;

import android.content.Context;

import com.wiacek.githubviewer.GithubViewerApplication;
import com.wiacek.githubviewer.di.scopes.ApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class AppModule {
    private GithubViewerApplication githubViewerApplication;

    public AppModule(GithubViewerApplication githubViewerApplication) {
        this.githubViewerApplication = githubViewerApplication;
    }

    @Provides
    @Named("ApplicationContext")
    @ApplicationScope
    Context provideApplicationContext() {
        return githubViewerApplication.getApplicationContext();
    }

}
