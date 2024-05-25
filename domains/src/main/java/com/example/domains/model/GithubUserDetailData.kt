package com.example.domain.model

data class GithubUserDetailData(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String,
    val name: String? = "",
    val company: String? = "",
    val blog: String? = "",
    val location: String? = "",
    val bio: String? = "",
    val twitterUsername: String? = "",
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int
)