package com.wiacek.githubviewer.ui.base;

import io.realm.RealmModel;

/**
 * Created by wiacek.dawid@gmail.com
 */

public abstract class ItemViewModel<T extends RealmModel> extends ViewModel {

    public ItemViewModel() {
        super(null);
    }

    public abstract void setItem(T item);
}
