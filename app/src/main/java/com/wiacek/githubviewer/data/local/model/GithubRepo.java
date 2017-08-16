package com.wiacek.githubviewer.data.local.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubRepo extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private User user;

    public GithubRepo() {}

    public GithubRepo(String id, String name, User user) {
        this.setId(id);
        this.setName(name);
        this.setUser(user);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
