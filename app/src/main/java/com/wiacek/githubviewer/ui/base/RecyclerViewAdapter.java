package com.wiacek.githubviewer.ui.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.OrderedRealmCollection;
import io.realm.RealmModel;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by wiacek.dawid@gmail.com
 */

public abstract class RecyclerViewAdapter<REALM_MODEL_T extends RealmModel, VIEW_MODEL_T extends ItemViewModel<REALM_MODEL_T>>
        extends RealmRecyclerViewAdapter<REALM_MODEL_T, RecyclerViewAdapter.ItemViewHolder<REALM_MODEL_T, VIEW_MODEL_T>> {

    public RecyclerViewAdapter(@Nullable OrderedRealmCollection<REALM_MODEL_T> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder<REALM_MODEL_T, VIEW_MODEL_T> holder, int position) {
        holder.setItem(getRealmData().get(position));
    }

    public abstract OrderedRealmCollection<REALM_MODEL_T> getRealmData();

    @Override
    public int getItemCount() {
        int size = 0;
        if(getData() != null) {
            size = getData().size();
        }
        return size;
    }

    public static class ItemViewHolder<T extends RealmModel, VT extends ItemViewModel<T>>
            extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;

        public ItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(T item) {
            viewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}
