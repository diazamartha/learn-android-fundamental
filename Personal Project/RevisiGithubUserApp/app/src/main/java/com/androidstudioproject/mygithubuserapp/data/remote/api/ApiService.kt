package com.androidstudioproject.mygithubuserapp.data.remote.api

import com.androidstudioproject.mygithubuserapp.data.remote.response.DetailUserResponse
import com.androidstudioproject.mygithubuserapp.data.source.GithubResponse
import com.androidstudioproject.mygithubuserapp.data.remote.response.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSerchUsers(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}