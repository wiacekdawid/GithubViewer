package com.wiacek.githubviewer.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubRepoDto {
    @SerializedName("id")
    private final String id;
    @SerializedName("name")
    private final String name;
    @SerializedName("owner")
    private final UserDto user;

    public GithubRepoDto(String id, String name, UserDto user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserDto getUser() {
        return user;
    }
}
