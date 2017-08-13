package com.wiacek.githubviewer.api.model;

import java.util.List;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class UserGithubReposResponseDto {
    private final List<GithubRepoDto> listOfGithubRepos;

    public UserGithubReposResponseDto(List<GithubRepoDto> listOfGithubRepos) {
        this.listOfGithubRepos = listOfGithubRepos;
    }
}
