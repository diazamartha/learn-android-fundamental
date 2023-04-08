package com.androidstudioproject.mygithubuserapp.data.remote.response

data class DetailUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val followersUrl: String,
    val followingUrl: String,
    val name: String,
    val following: Int,
    val followers: Int

)
