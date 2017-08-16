package com.wiacek.githubviewer.ui.githublist;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wiacek.githubviewer.ui.base.RecyclerViewAdapter;
import com.wiacek.githubviewer.ui.base.RecyclerViewViewModel;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListViewModel extends RecyclerViewViewModel {

    private GithubListAdapter githubListAdapter;
    private LinearLayoutManager linearLayoutManager;

    public GithubListViewModel(GithubListAdapter githubListAdapter, LinearLayoutManager linearLayoutManager, @Nullable State savedInstanceState) {
        super(savedInstanceState);
        this.githubListAdapter = githubListAdapter;
        this.linearLayoutManager = linearLayoutManager;

    }
    @Override
    protected RecyclerViewAdapter getAdapter() {
        return githubListAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return linearLayoutManager;
    }
}
