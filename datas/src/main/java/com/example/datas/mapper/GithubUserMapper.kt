package com.example.data.mapper

import com.example.data.dto.GithubUserDetailDto
import com.example.data.dto.GithubUserSearchDto
import com.example.domain.model.GithubUserData
import com.example.domain.model.GithubUserDetailData
import com.example.domain.model.GithubUserSearchData

class GithubUserMapper {

    fun mapGithubUserSearchDto(remoteGithubUserFromDto: GithubUserSearchDto): GithubUserSearchData {
        return GithubUserSearchData(
            remoteGithubUserFromDto.totalCount,
            remoteGithubUserFromDto.items.map {
                GithubUserData(
                    it.login,
                    it.avatarUrl
                )
            }
        )
    }

    fun mapGithubUserDetailDto(remoteGithubUserDetailFromDto: GithubUserDetailDto): GithubUserDetailData {
        return GithubUserDetailData(
            remoteGithubUserDetailFromDto.login,
            remoteGithubUserDetailFromDto.avatarUrl,
            remoteGithubUserDetailFromDto.htmlUrl,
            remoteGithubUserDetailFromDto.name,
            remoteGithubUserDetailFromDto.company,
            remoteGithubUserDetailFromDto.blog,
            remoteGithubUserDetailFromDto.location,
            remoteGithubUserDetailFromDto.bio,
            remoteGithubUserDetailFromDto.twitterUsername,
            remoteGithubUserDetailFromDto.publicRepos,
            remoteGithubUserDetailFromDto.publicGists,
            remoteGithubUserDetailFromDto.followers,
            remoteGithubUserDetailFromDto.following
        )
    }
}
