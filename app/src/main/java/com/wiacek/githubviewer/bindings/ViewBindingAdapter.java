package com.wiacek.githubviewer.bindings;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class ViewBindingAdapter {
    @BindingAdapter("android:visibility")
    public static void bindVisibility(View view, boolean isVisible) {
        if(isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
