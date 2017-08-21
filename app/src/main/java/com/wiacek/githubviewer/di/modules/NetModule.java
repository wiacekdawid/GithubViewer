package com.wiacek.githubviewer.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wiacek.githubviewer.BuildConfig;
import com.wiacek.githubviewer.api.GithubService;
import com.wiacek.githubviewer.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wiacek.dawid@gmail.com
 */

@Module
public class NetModule {

    @Provides
    @ApplicationScope
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.GITHUB_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    GithubService provideGithubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }
}
