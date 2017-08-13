package com.wiacek.githubviewer.di.components;

import com.wiacek.githubviewer.di.modules.GithubListActivityModule;
import com.wiacek.githubviewer.di.scopes.ActivityScope;
import com.wiacek.githubviewer.ui.githublist.GithubListActivity;

import dagger.Subcomponent;

/**
 * Created by wiacek.dawid@gmail.com
 */

@ActivityScope
@Subcomponent(modules = {GithubListActivityModule.class})
public interface GithubListActivityComponent {
    void inject(GithubListActivity activity);
}
