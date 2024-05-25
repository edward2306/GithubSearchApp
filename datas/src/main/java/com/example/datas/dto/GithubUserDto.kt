package com.example.data.dto

import com.squareup.moshi.Json

data class GithubUserDto(
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String
)