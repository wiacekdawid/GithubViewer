package com.wiacek.githubviewer.di.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.wiacek.githubviewer.api.GithubService;
import com.wiacek.githubviewer.data.GithubReposRepository;
import com.wiacek.githubviewer.data.local.util.GithubRepoDataHelper;
import com.wiacek.githubviewer.di.scopes.ActivityScope;
import com.wiacek.githubviewer.ui.base.ViewModel;
import com.wiacek.githubviewer.ui.githublist.GithubListActivity;
import com.wiacek.githubviewer.ui.githublist.GithubListAdapter;
import com.wiacek.githubviewer.ui.githublist.GithubListViewModel;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class GithubListActivityModule {
    private WeakReference<GithubListActivity> activity;
    private ViewModel.State viewModelState;

    public GithubListActivityModule(GithubListActivity activity, ViewModel.State viewModelState) {
        this.activity = new WeakReference<GithubListActivity>(activity);
        this.viewModelState = viewModelState;
    }

    @Provides
    @ActivityScope
    GithubReposRepository provideGithubReposRepository(GithubService githubService) {
        return new GithubReposRepository(githubService);
    }

    @Provides
    @ActivityScope
    GithubListAdapter proviedGithubListAdapter() {
        return new GithubListAdapter(GithubRepoDataHelper.getReposByOwnerName(Realm.getDefaultInstance(), "JakeWharton"), true);
    }

    @Provides
    @ActivityScope
    LinearLayoutManager proviedLinearLayoutManager(@Named("ApplicationContext") Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    @ActivityScope
    GithubListViewModel proviedGithubListViewModel(GithubListAdapter githubListAdapter, LinearLayoutManager linearLayoutManager) {
        return new GithubListViewModel(githubListAdapter, linearLayoutManager, viewModelState);
    }
}
