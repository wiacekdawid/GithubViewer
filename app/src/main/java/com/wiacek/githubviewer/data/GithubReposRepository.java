package com.wiacek.githubviewer.data;

import com.wiacek.githubviewer.api.GithubService;
import com.wiacek.githubviewer.api.model.GithubRepoDto;
import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.data.local.model.GithubReposMapper;
import com.wiacek.githubviewer.data.local.util.GithubRepoDataHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubReposRepository {

    private GithubService githubService;

    public GithubReposRepository(GithubService githubService) {
        this.githubService = githubService;
    }

    public Single<List<GithubRepo>> getGithubRepos(String userName) {
        return githubService.getUserRepos(userName)
                .map(
                        githubRepoDtos ->
                        {
                            List<GithubRepo> list = new ArrayList<>(100);
                            for(GithubRepoDto githubRepoDto: githubRepoDtos) {
                                list.add(GithubReposMapper.trasformGithubRepoDtoToGithubRepo(githubRepoDto));
                            }
                            return list;
                        }
                )
                .doOnSuccess(list -> GithubRepoDataHelper.add(Realm.getDefaultInstance(), list).subscribe());
    }
}
