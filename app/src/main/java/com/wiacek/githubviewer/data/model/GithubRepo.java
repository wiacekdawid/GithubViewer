package com.wiacek.githubviewer.data.model;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubRepo {
    public String id;
    public String name;
    public User user;

    public GithubRepo(String id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
