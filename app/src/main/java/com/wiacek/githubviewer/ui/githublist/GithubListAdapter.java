package com.wiacek.githubviewer.ui.githublist;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiacek.githubviewer.R;
import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.databinding.ItemGithubListBinding;
import com.wiacek.githubviewer.ui.base.RecyclerViewAdapter;

import io.realm.OrderedRealmCollection;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListAdapter extends RecyclerViewAdapter<GithubRepo, GithubItemViewModel> {

    public GithubListAdapter(@Nullable OrderedRealmCollection<GithubRepo> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public OrderedRealmCollection<GithubRepo> getRealmData() {
        return getData();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_github_list, parent, false);

        GithubItemViewModel githubItemViewModel = new GithubItemViewModel();

        ItemGithubListBinding binding = ItemGithubListBinding.bind(view);
        binding.setViewModel(githubItemViewModel);

        return new ItemViewHolder(view, binding, githubItemViewModel);
    }
}
