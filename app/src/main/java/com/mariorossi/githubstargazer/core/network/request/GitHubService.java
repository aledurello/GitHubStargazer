package com.mariorossi.githubstargazer.core.network.request;

import com.mariorossi.githubstargazer.core.model.Repository;
import com.mariorossi.githubstargazer.core.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repository>> getListUserRepositories(@Path("user") String user);

    @GET("repos/{user}/{repository}/stargazers")
    Call<List<User>> requestRepositoryStargazers(@Path("user") String userName, @Path("repository") String repositoryName);
}