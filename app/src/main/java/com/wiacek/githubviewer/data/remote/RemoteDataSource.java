package com.wiacek.githubviewer.data.remote;

import com.wiacek.githubviewer.BuildConfig;
import com.wiacek.githubviewer.api.GithubService;
import com.wiacek.githubviewer.api.model.GithubRepoDto;
import com.wiacek.githubviewer.data.local.model.GithubRepo;
import com.wiacek.githubviewer.data.local.model.GithubReposMapper;
import com.wiacek.githubviewer.data.local.util.GithubRepoDataHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.realm.Realm;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by wiacek.dawid@gmail.com
 */

public class RemoteDataSource {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PER_PAGE = 15;
    private static final String REGEX_IS_NEXT_PAGE_LINK = ".*<([a-zA-Z0-9:./?=&_]+)>; rel=\"next\".*";
    private static final String HEADER_LINK = "link";

    private GithubService githubService;
    private String nextPage;
    private long beginingGithubRepoCount = 0;

    public RemoteDataSource(GithubService githubService) {
        this.githubService = githubService;
    }

    public Observable<Boolean> getGithubRepos() {
        beginingGithubRepoCount = GithubRepoDataHelper.getGithubRepoCount(Realm.getDefaultInstance());
        return getGithubReposObservable()
                .map(list -> nextPage != null);
    }

    private Observable<List<GithubRepo>> getGithubReposObservable() {
        return getGithubReposFromApi()
                .doOnNext(listResponse -> checkIfNextPageLinkInHeader(listResponse))
                .map(listResponse ->
                {
                    List<GithubRepo> list = new ArrayList<>();
                    for(GithubRepoDto githubRepoDto: listResponse.body()) {
                        list.add(GithubReposMapper.trasformGithubRepoDtoToGithubRepo(githubRepoDto));
                    }
                    return list;
                })
                .doOnNext(list -> GithubRepoDataHelper.add(Realm.getDefaultInstance(), list)
                        .doOnError(throwable -> Timber.e(throwable.getMessage()))
                        .subscribe())
                .filter(list -> !isLastPage())
                .flatMap(list -> this.getNextPageGithubReposObservable(list));

    }

    private Observable<Response<List<GithubRepoDto>>> getGithubReposFromApi() {
        if(nextPage == null) {
            return githubService.getUserRepos(BuildConfig.GITHUB_API_SEARCHED_USER,
                    BuildConfig.GITHUB_ACCESS_TOKEN,
                    DEFAULT_PER_PAGE,
                    DEFAULT_PAGE);
        }
        return githubService.getUserReposNextPage(nextPage);
    }

    private Observable<List<GithubRepo>> getNextPageGithubReposObservable(List<GithubRepo> githubRepoList) {
        Observable<List<GithubRepo>> githubReposPageObservable = Observable.just(githubRepoList);
        Observable<List<GithubRepo>> nextGithubReposPageObservable = getGithubReposObservable();
        return Observable.merge(githubReposPageObservable, nextGithubReposPageObservable);
    }

    private void checkIfNextPageLinkInHeader(Response<List<GithubRepoDto>> response) {
        nextPage = null;
        String linkHeader = response.headers().get(HEADER_LINK);
        if (linkHeader != null) {
            Pattern patternIsNextPageLink = Pattern.compile(REGEX_IS_NEXT_PAGE_LINK);
            Matcher matcherIsNextPageLink = patternIsNextPageLink.matcher(linkHeader);
            if (matcherIsNextPageLink.find()) {
                nextPage = matcherIsNextPageLink.group(1);
            }
        }
    }

    private boolean isLastPage() {
        long size = GithubRepoDataHelper.getGithubRepoCount(Realm.getDefaultInstance()) - beginingGithubRepoCount;
        return (size >= DEFAULT_PER_PAGE || nextPage == null);
    }
}
