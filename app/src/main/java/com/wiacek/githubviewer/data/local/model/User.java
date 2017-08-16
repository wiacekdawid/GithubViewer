package com.wiacek.githubviewer.data.local.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class User extends RealmObject {
    @PrimaryKey
    private String id;
    private String login;
    private String avatarUrl;

    public User() {}

    public User(String id, String login, String avatarUrl) {
        this.setId(id);
        this.setLogin(login);
        this.setAvatarUrl(avatarUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
