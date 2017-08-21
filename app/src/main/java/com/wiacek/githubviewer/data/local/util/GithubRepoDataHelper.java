package com.wiacek.githubviewer.data.local.util;

import io.reactivex.Completable;
import io.realm.Realm;
import io.realm.RealmResults;
import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.data.local.model.GithubRepoFields;

import java.util.List;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubRepoDataHelper {
    public static RealmResults<GithubRepo> getReposByOwnerName(Realm realm, String ownerName) {
        return realm.where(GithubRepo.class)
                .equalTo(GithubRepoFields.USER.LOGIN, ownerName)
                .findAll();
    }

    public static Completable add(Realm realm, List<GithubRepo> list) {
        return Completable.fromAction(() -> realm.executeTransaction(
                innerRealm -> innerRealm.insertOrUpdate(list)
        ));
    }

    public static long getGithubRepoCount(Realm realm) {
        return realm.where(GithubRepo.class).count();
    }
}
