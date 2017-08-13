package com.wiacek.githubviewer.api;


import com.wiacek.githubviewer.api.model.GithubRepoDto;
import com.wiacek.githubviewer.api.model.UserGithubReposResponseDto;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wiacek.dawid@gmail.com
 */

public interface GithubService {
    @GET("/users/{userName}/repos")
    Single<List<GithubRepoDto>> getUserRepos(@Path("userName") String userName);
}
