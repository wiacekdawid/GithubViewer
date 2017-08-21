package com.wiacek.githubviewer.ui.githublist;

import android.databinding.Bindable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.wiacek.githubviewer.BR;
import com.wiacek.githubviewer.data.remote.RemoteDataSource;
import com.wiacek.githubviewer.ui.base.RecyclerViewAdapter;
import com.wiacek.githubviewer.ui.base.RecyclerViewViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class GithubListViewModel extends RecyclerViewViewModel {

    private GithubListAdapter githubListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private RemoteDataSource remoteDataSource;

    private boolean isNextPageAvailable = true;
    private boolean isLoading;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Bindable
    public boolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        notifyPropertyChanged(BR.isLoading);
    }

    public GithubListViewModel(RemoteDataSource remoteDataSource,
                               GithubListAdapter githubListAdapter,
                               LinearLayoutManager linearLayoutManager,
                               @Nullable State savedInstanceState) {
        super(savedInstanceState);
        this.githubListAdapter = githubListAdapter;
        this.linearLayoutManager = linearLayoutManager;
        this.remoteDataSource = remoteDataSource;
        this.isNextPageAvailable = true;
    }

    @Override
    protected RecyclerViewAdapter getAdapter() {
        return githubListAdapter;
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        return linearLayoutManager;
    }

    @Override
    protected void loadMoreItems() {
        if(!isLoading && isNextPageAvailable) {
            Log.i("isLoading0 ", String.valueOf(isLoading));
            setIsLoading(true);
            Disposable disposable = remoteDataSource.getGithubRepos()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(value -> { isNextPageAvailable = value; setIsLoading(false); Log.i("isLoading1 ", String.valueOf(isLoading));},
                            t -> { Timber.e(t.getMessage()); setIsLoading(false);  Log.i("isLoading2 ", String.valueOf(isLoading));},
                            () -> { setIsLoading(false);  Log.i("isLoading3 ", String.valueOf(isLoading));});
            compositeDisposable.add(disposable);
        }
    }

    public void detachFromView() {
        if(compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }
}
