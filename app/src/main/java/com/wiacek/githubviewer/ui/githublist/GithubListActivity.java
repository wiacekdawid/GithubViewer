package com.wiacek.githubviewer.ui.githublist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wiacek.githubviewer.GithubViewerApplication;
import com.wiacek.githubviewer.R;
import com.wiacek.githubviewer.data.local.util.GithubRepoDataHelper;
import com.wiacek.githubviewer.databinding.ActivityGithubListBinding;
import com.wiacek.githubviewer.di.components.GithubListActivityComponent;
import com.wiacek.githubviewer.di.modules.GithubListActivityModule;
import com.wiacek.githubviewer.ui.base.ViewModel;

import javax.inject.Inject;

import io.realm.Realm;


/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListActivity extends AppCompatActivity {
    @Inject
    protected GithubListViewModel githubListViewModel;

    private static final String VIEW_MODEL_STATE = "viewModelState";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ViewModel.State state = null;
        if(savedInstanceState != null) {
            state = savedInstanceState.getParcelable(VIEW_MODEL_STATE);
        }

        GithubListActivityComponent component = GithubViewerApplication
                 .getGithubViewerApplication(this)
                 .getAppComponent()
                 .add(new GithubListActivityModule(this, state));

        component.inject(this);

        super.onCreate(savedInstanceState);

        ActivityGithubListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_github_list);
        binding.setViewModel(githubListViewModel);
        githubListViewModel.loadMoreItems();
    }

    @Override
    protected void onDestroy() {
        if(githubListViewModel != null) {
            githubListViewModel.detachFromView();
        }
        super.onDestroy();
    }
}
