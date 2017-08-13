package com.wiacek.githubviewer.ui.githublist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wiacek.githubviewer.GithubViewerApplication;
import com.wiacek.githubviewer.R;
import com.wiacek.githubviewer.data.GithubReposRepository;
import com.wiacek.githubviewer.data.model.GithubRepo;
import com.wiacek.githubviewer.databinding.ActivityGithubListBinding;
import com.wiacek.githubviewer.di.components.GithubListActivityComponent;
import com.wiacek.githubviewer.di.modules.GithubListActivityModule;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListActivity extends AppCompatActivity {
    @Inject
    protected GithubListViewModel githubListViewModel;
    @Inject
    protected GithubReposRepository githubReposRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
         GithubListActivityComponent component = GithubViewerApplication
                 .getGithubViewerApplication(this)
                 .getAppComponent()
                 .add(new GithubListActivityModule(this));

        component.inject(this);

        super.onCreate(savedInstanceState);

        ActivityGithubListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_github_list);
        binding.setViewModel(githubListViewModel);

        githubReposRepository.getGithubRepos("JakeWharton")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(githubRepos -> {
                    for(GithubRepo repo : githubRepos) {
                        githubListViewModel.setText(githubListViewModel.getText() + repo.name);
                    }
                },
                        t -> Timber.e(t.getMessage()));
    }
}
