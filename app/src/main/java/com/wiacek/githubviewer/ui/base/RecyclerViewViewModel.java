package com.wiacek.githubviewer.ui.base;

import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wiacek.githubviewer.BR;

/**
 * Created by wiacek.dawid@gmail.com
 */

public abstract class RecyclerViewViewModel extends ViewModel {
    LinearLayoutManager layoutManager;
    private Parcelable savedLayoutManagerState;

    protected abstract RecyclerViewAdapter getAdapter();
    protected abstract LinearLayoutManager getLayoutManager();
    protected abstract void loadMoreItems();

    public RecyclerViewViewModel(@Nullable State savedInstanceState) {
        super(savedInstanceState);
        if (savedInstanceState instanceof RecyclerViewViewModelState) {
            savedLayoutManagerState =
                    ((RecyclerViewViewModelState) savedInstanceState).layoutManagerState;
        }
    }

    @Override
    public RecyclerViewViewModelState getInstanceState() {
        return new RecyclerViewViewModelState(this);
    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        layoutManager = getLayoutManager();
        if (savedLayoutManagerState != null) {
            layoutManager.onRestoreInstanceState(savedLayoutManagerState);
            savedLayoutManagerState = null;
        }
        recyclerView.setAdapter(getAdapter());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(totalItemCount <= (lastVisibleItem + 2)){
                    loadMoreItems();
                }
            }
        });
    }

    protected static class RecyclerViewViewModelState extends State {

        private final Parcelable layoutManagerState;

        public RecyclerViewViewModelState(RecyclerViewViewModel viewModel) {
            super(viewModel);
            layoutManagerState = viewModel.layoutManager.onSaveInstanceState();
        }

        public RecyclerViewViewModelState(Parcel in) {
            super(in);
            layoutManagerState = in.readParcelable(
                    RecyclerView.LayoutManager.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeParcelable(layoutManagerState, flags);
        }

        public static Creator<RecyclerViewViewModelState> CREATOR =
                new Creator<RecyclerViewViewModelState>() {
                    @Override
                    public RecyclerViewViewModelState createFromParcel(Parcel source) {
                        return new RecyclerViewViewModelState(source);
                    }

                    @Override
                    public RecyclerViewViewModelState[] newArray(int size) {
                        return new RecyclerViewViewModelState[size];
                    }
                };
    }
}
