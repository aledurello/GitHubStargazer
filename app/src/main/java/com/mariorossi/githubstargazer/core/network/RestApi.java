package com.mariorossi.githubstargazer.core.network;

import com.mariorossi.githubstargazer.core.model.Repository;
import com.mariorossi.githubstargazer.core.model.User;
import com.mariorossi.githubstargazer.core.network.request.GitHubService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit retrofit;

    static {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RestApi() {

    }

    public static Call requestUserRepositories(String name, Callback<List<Repository>> response) {
        Call<List<Repository>> call = retrofit
                .create(GitHubService.class)
                .getListUserRepositories(name);
        call.enqueue(response);
        return call;
    }

    public static Call requestRepositoryStargazers(String userName, String repositoryName, Callback<List<User>> response)  {
        Call<List<User>> call = retrofit
                .create(GitHubService.class)
                .requestRepositoryStargazers(userName, repositoryName);
        call.enqueue(response);
        return call;
    }
}