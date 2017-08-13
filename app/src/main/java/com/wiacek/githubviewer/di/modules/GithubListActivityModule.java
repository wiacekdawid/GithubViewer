package com.wiacek.githubviewer.di.modules;

import com.wiacek.githubviewer.api.GithubService;
import com.wiacek.githubviewer.data.GithubReposRepository;
import com.wiacek.githubviewer.di.scopes.ActivityScope;
import com.wiacek.githubviewer.ui.githublist.GithubListActivity;
import com.wiacek.githubviewer.ui.githublist.GithubListViewModel;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class GithubListActivityModule {
    private WeakReference<GithubListActivity> activity;

    public GithubListActivityModule(GithubListActivity activity) {
        this.activity = new WeakReference<GithubListActivity>(activity);
    }

    @Provides
    @ActivityScope
    GithubReposRepository provideGithubReposRepository(GithubService githubService) {
        return new GithubReposRepository(githubService);
    }

    @Provides
    @ActivityScope
    GithubListViewModel proviedGithubListViewModel() {
        return new GithubListViewModel();
    }
}
