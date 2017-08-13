package com.wiacek.githubviewer.data.model;

import com.wiacek.githubviewer.api.model.GithubRepoDto;
import com.wiacek.githubviewer.api.model.UserDto;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubReposMapper {
    public static GithubRepo trasformGithubRepoDtoToGithubRepo(GithubRepoDto githubRepoDto) {
        User user = GithubReposMapper.transformUserDtoToUser(githubRepoDto.getUser());
        return new GithubRepo(githubRepoDto.getId(), githubRepoDto.getName(), user);
    }

    public static User transformUserDtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getLogin(), userDto.getAvatarUrl());
    }
}
