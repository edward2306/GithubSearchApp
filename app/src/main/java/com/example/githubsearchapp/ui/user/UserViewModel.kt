package com.example.githubsearchapp.ui.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.GithubUserDb
import com.example.data.mapper.GithubUserMapper
import com.example.domain.data.Resource
import com.example.domain.model.GithubUserDetailData
import com.example.domain.model.GithubUserSearchData
import com.example.domain.repository.GithubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val db: GithubUserDb,
    private val repository: GithubUserRepository,
    private val mapper: GithubUserMapper
) : ViewModel() {

    private val userSearchList = MutableLiveData<GithubUserSearchData?>()

    val githubUserSearchList: MutableLiveData<GithubUserSearchData?>
        get() = userSearchList

    private val userDetail = MutableLiveData<GithubUserDetailData?>()
    val githubUserDetail: MutableLiveData<GithubUserDetailData?>
        get() = userDetail

    private val _errorMessage = MutableLiveData<String?>()

    val errorMessage: MutableLiveData<String?>
        get() = _errorMessage

    fun userSearch(username: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            when (val result = repository.searchUser(username, page, perPage)) {
                is Resource.Success -> {
                    githubUserSearchList.value = result.data
                }

                is Resource.Error -> {
                    _errorMessage.value = result.message
                }

            }

        }
    }

    private fun fetchUserDetail(username: String) {
        viewModelScope.launch {
            when (val result = repository.getUserDetail(username)) {
                is Resource.Success -> {
                    userDetail.value = result.data
                }

                is Resource.Error -> _errorMessage.value = result.message
            }
        }
    }

    fun fetchUserDetailCache(username: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var errorThrown = true
                try {
                    val cache = db.githubUserDetailDao.getUserDetail(username)
                    userDetail.value = mapper.mapGithubUserDetailDto(cache)
                    errorThrown = false
                } catch (e: Exception) {
                    Log.e("Error: ", errorMessage.toString())
                } finally {
                    if (errorThrown)
                        fetchUserDetail(username)
                }
            }
        }
    }
}