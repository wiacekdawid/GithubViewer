package com.wiacek.githubviewer.ui.githublist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wiacek.githubviewer.BR;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListViewModel extends BaseObservable {

    private String text;

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
