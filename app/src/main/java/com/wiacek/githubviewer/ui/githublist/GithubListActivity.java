package com.wiacek.githubviewer.ui.githublist;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wiacek.githubviewer.R;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_github_list);
    }
}
