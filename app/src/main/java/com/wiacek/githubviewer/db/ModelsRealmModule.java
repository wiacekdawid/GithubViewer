package com.wiacek.githubviewer.db;

/**
 * Created by wiacek.dawid@gmail.com
 */

import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.data.local.model.User;

import javax.inject.Inject;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {GithubRepo.class, User.class})
public class ModelsRealmModule {
    @Inject
    ModelsRealmModule() {}
}
