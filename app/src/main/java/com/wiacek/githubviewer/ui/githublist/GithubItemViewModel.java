package com.wiacek.githubviewer.ui.githublist;

import android.databinding.Bindable;

import com.wiacek.githubviewer.BR;
import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.ui.base.ItemViewModel;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubItemViewModel extends ItemViewModel<GithubRepo> {
    private String name;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Override
    public void setItem(GithubRepo githubRepo) {
        this.name = githubRepo.getName();
        notifyChange();
    }
}
