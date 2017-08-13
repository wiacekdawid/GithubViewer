package com.wiacek.githubviewer.data.model;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class User {
    public String id;
    public String login;
    public String avatarUrl;

    public User(String id, String login, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }
}
