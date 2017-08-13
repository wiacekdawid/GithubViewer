package com.wiacek.githubviewer.di.components;

import com.wiacek.githubviewer.GithubViewerApplication;
import com.wiacek.githubviewer.di.modules.AppModule;
import com.wiacek.githubviewer.di.modules.GithubListActivityModule;
import com.wiacek.githubviewer.di.modules.NetModule;
import com.wiacek.githubviewer.di.scopes.ApplicationScope;

import dagger.Component;

/**
 * Created by wiacek.dawid@gmail.com
 */

@ApplicationScope
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(GithubViewerApplication githubViewerApplication);
    GithubListActivityComponent add(GithubListActivityModule module);
}
