package com.wiacek.githubviewer.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.wiacek.githubviewer.ui.base.RecyclerViewViewModel;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class RecyclerViewBindingAdapter {
    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                RecyclerViewViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}
