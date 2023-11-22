package com.chefio.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.common.base.BaseViewModel
import com.common.data.network.model.UserInfo
import com.common.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {
    private val _mtUserInfo = MutableLiveData<List<UserInfo>>()
    val userInfo: LiveData<List<UserInfo>> = _mtUserInfo

    private val _mtUserInfoError = MutableLiveData<Throwable>()
    val mtUserInfoError: LiveData<Throwable> = _mtUserInfoError

    fun getUserInfoFromMT() {
//        viewModelScope.launch {
//            val mtUser = apiRepository.getUsers()
//            processDataEvent(mtUser, onError = {
//                _mtUserInfoError.postValue(it)
//            }) {
//                _mtUserInfo.postValue(it)
//            }
//        }
    }

}