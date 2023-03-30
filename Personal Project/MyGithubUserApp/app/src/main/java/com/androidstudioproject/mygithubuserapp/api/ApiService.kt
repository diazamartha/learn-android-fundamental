package com.androidstudioproject.mygithubuserapp.api

import com.androidstudioproject.mygithubuserapp.source.DetailUserResponse
import com.androidstudioproject.mygithubuserapp.source.source.GithubResponse
import com.androidstudioproject.mygithubuserapp.source.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSerchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}