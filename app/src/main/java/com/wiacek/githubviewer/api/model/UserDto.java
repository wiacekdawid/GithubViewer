package com.wiacek.githubviewer.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class UserDto {
    @SerializedName("id")
    private final String id;
    @SerializedName("login")
    private final String login;
    @SerializedName("avatar_url")
    private final String avatarUrl;

    public UserDto(String id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
