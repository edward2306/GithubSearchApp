package com.example.data.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_detail")
data class GithubUserDetailDto(
    @PrimaryKey
    @field:Json(name = "login")
    val login: String,
    @field:Json(name = "avatar_url")
    val avatarUrl: String,
    @field:Json(name = "html_url")
    val htmlUrl: String,
    @field:Json(name = "name")
    val name: String? = "",
    @field:Json(name = "company")
    val company: String? = "",
    @field:Json(name = "blog")
    val blog: String? = "",
    @field:Json(name = "location")
    val location: String? = "",
    @field:Json(name = "bio")
    val bio: String? = "",
    @field:Json(name = "twitter_username")
    val twitterUsername: String? = "",
    @field:Json(name = "public_repos")
    val publicRepos: Int,
    @field:Json(name = "public_gists")
    val publicGists: Int,
    @field:Json(name = "followers")
    val followers: Int,
    @field:Json(name = "following")
    val following: Int
) : Parcelable, Comparable<GithubUserDetailDto> {

    override fun compareTo(other: GithubUserDetailDto): Int = compareBy(
        GithubUserDetailDto::login,
        GithubUserDetailDto::avatarUrl,
        GithubUserDetailDto::htmlUrl,
        GithubUserDetailDto::name,
        GithubUserDetailDto::company,
        GithubUserDetailDto::blog,
        GithubUserDetailDto::location,
        GithubUserDetailDto::bio,
        GithubUserDetailDto::twitterUsername,
        GithubUserDetailDto::publicRepos,
        GithubUserDetailDto::publicGists,
        GithubUserDetailDto::followers,
        GithubUserDetailDto::following,
    ).compare(this, other)

    companion object : Parceler<GithubUserDetailDto> {
        override fun GithubUserDetailDto.write(parcel: Parcel, flags: Int) {
            parcel.writeString(login)
            parcel.writeString(avatarUrl)
            parcel.writeString(htmlUrl)
            parcel.writeString(name)
            parcel.writeString(company)
            parcel.writeString(blog)
            parcel.writeString(location)
            parcel.writeString(bio)
            parcel.writeString(twitterUsername)
            parcel.writeInt(publicRepos)
            parcel.writeInt(publicGists)
            parcel.writeInt(followers)
            parcel.writeInt(following)
        }

        override fun create(parcel: Parcel): GithubUserDetailDto = GithubUserDetailDto(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
        )
    }
}