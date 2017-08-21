package com.wiacek.githubviewer.api;


import com.wiacek.githubviewer.api.model.GithubRepoDto;
import com.wiacek.githubviewer.api.model.UserGithubReposResponseDto;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by wiacek.dawid@gmail.com
 */

public interface GithubService {
    @GET("/users/{userName}/repos")
    Observable<Response<List<GithubRepoDto>>> getUserRepos(@Path("userName") String userName,
                                                           @Query("access_token") String accessToken,
                                                           @Query("per_page") int perPage,
                                                           @Query("page") int page);

    @GET
    Observable<Response<List<GithubRepoDto>>> getUserReposNextPage(@Url String url);
}
